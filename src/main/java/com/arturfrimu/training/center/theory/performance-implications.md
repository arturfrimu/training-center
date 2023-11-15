### Performance implications
Rich Domain Model brings overhead for sure. However, there are  some workarounds to reach compromise.

### Optimization of queries
Firstly, let’s have a look at PocketService.updateTamagotchi method again:

```java
@Service
@RequiredArgsConstructor
class PocketService {
    /* other fields and methods */

    @Transactional
    public void updateTamagotchi(UUID tamagotchiId, TamagotchiUpdateRequest request) {
        UUID pocketId = em.createQuery(
                "SELECT t.pocket.id AS id FROM Tamagotchi t WHERE t.id = :tamagotchiId",
                UUID.class
        )
        .setParameter("tamagotchiId", tamagotchiId)
        .getSingleResult();

        Pocket pocket = em.find(Pocket.class, pocketId);
        pocket.updateTamagotchi(tamagotchiId, request);
    }
}
```
The problem is that we retrieve all existing Tamagotchi instances for a specified Pocket when we actually want to update a single one. 
Look at the log below:

```genericsql
select t1_0.pocket_id from tamagotchi t1_0 where t1_0.id=?

select p1_0.id,p1_0.name from pocket p1_0 where p1_0.id=?

select t1_0.pocket_id,t1_0.id,t1_0.name,t1_0.status from tamagotchi t1_0 where t1_0.pocket_id=?
```

We can change queries to restrict the transmission of unnecessary data. Look at the code example below:

```java
@Service
@RequiredArgsConstructor
public class PocketService {
    /* other fields and methods */

    @Transactional
    public void updateTamagotchi(UUID tamagotchiId, TamagotchiUpdateRequest request) {
        Pocket pocket = em.createQuery(
                        """
                        SELECT p FROM Pocket p
                        LEFT JOIN FETCH p.tamagotchis t
                        WHERE t.id = :tamagotchiId
                        """,
                        Pocket.class
                ).setParameter("tamagotchiId", tamagotchiId)
                .getSingleResult();
        pocket.updateTamagotchi(tamagotchiId, request);
    }
}
```
Instead of selecting all existing Tamagotchi instances for the specified Pocket, we retrieve Pocket and the only associated 
Tamagotchi instance by specified id. Log also looks differently:

```sql
select
p1_0.id,
p1_0.name,
t1_0.pocket_id,
t1_0.id,
t1_0.name,
t1_0.status
from pocket p1_0
left join tamagotchi t1_0 on p1_0.id=t1_0.pocket_id
where t1_0.id=?
```
Even if Pocket contains thousands of Tamagotchi, it won’t affect the performance of the application. 
Because it will retrieve only a single one. If you run test cases from the previous paragraph, they will also pass successfully.

Pinpointing optimized checks
Nevertheless, the previous technique has limitations. 
To understand this, let’s write another test. As we’ve already discussed, the business rule demands that each 
Tamagotchi must have a unique name within Pocket. Let's test this behaviour. Look at the code snippet below:

```java
    class  Test {
    
    @Test
    void shouldUpdateTamagotchiIfThereAreMultipleOnes() {
        UUID pocketId = pocketService.createPocket("New pocket");
        UUID tamagotchiId = pocketService.createTamagotchi(
                pocketId,
                new TamagotchiCreateRequest("Cat", CREATED)
        );
        pocketService.createTamagotchi(
                pocketId,
                new TamagotchiCreateRequest("Dog", CREATED)
        );

        assertThrows(
                TamagotchiNameInvalidException.class,
                () -> pocketService.updateTamagotchi(tamagotchiId, new TamagotchiUpdateRequest("Dog", CREATED))
        );
    }
}
```
There are two Tamagotchi with names of Cat and Dog. 
We try to rename Cat to Dog. Here, we expect to get TamagotchiNameInvalidException. 
Because business rule should validate this scenario. But if you run the test, you’ll get this result:

`Expected com.example.demo.domain.exception.TamagotchiNameInvalidException to Expected com.example.demo.domain.exception.TamagotchiNameInvalidException to be thrown, but nothing was thrown.`
Why is that? Look again at Pocket.updateTamagotchi method declaration:

```java
class Test {
    public void updateTamagotchi(UUID tamagotchiId, TamagotchiUpdateRequest request) {
        Tamagotchi tamagotchi = tamagotchiById(tamagotchiId);
        tamagotchi.changeName(request.name());
        tamagotchi.changeStatus(request.status());
        validateTamagotchiNamesUniqueness();
    }

    private void validateTamagotchiNamesUniqueness() {
        Set<String> names = new HashSet<>();
        for (Tamagotchi tamagotchi : tamagotchis) {
            if (!names.add(tamagotchi.getName())) {
                throw new TamagotchiNameInvalidException(
                        "Tamagotchi name is not unique: " + tamagotchi.getName());
            }
        }
    }
}
```
As you can see, Pocket aggregate expects to have access for all Tamagotchi to validate the business rule. 
But we changed the query to select only a single Tamagotchi (the one we want to update). 
That’s why the exception is not raised. 
Because there is always a single Tamagotchi on the list and we cannot violate the uniqueness.

I see people trying to remove such validations from an aggregate. 
But I think you shouldn’t do that. Instead, it’s better to perform another optimized check in the service level in advance. 
To understand this approach, look at the schema below:

![plot](/Users/arturfrimu/Desktop/training-center/training-center/src/main/resources/static/images/perform-another-optimized-check.webp)

Additional validations in service layer
Aggregate should always be valid. You can’t predict all likely future outcomes. 
Maybe you’ll call Pocket in another scenario. 
So, if you drop a check from an aggregate completely, you may accidentally violate business rule.

Nevertheless, we live in a real world where performance matters. 
It’s much better to execute a single exists SQL statement then retrieve all Tamagotchi instances from the database. 
So, you put optimized check specifically where it’s needed. But you also leave the aggregate untouched.

Look at the final code snippet of PocketService.updateTamagotchi method:

```java
class Test {
    @Transactional
    public void updateTamagotchi(UUID tamagotchiId, TamagotchiUpdateRequest request) {
        boolean nameIsNotUnique = em.createQuery(
                """
                        SELECT COUNT(t) > 0 FROM Tamagotchi t
                        WHERE t.id <> :tamagotchiId AND t.name = :newName
                        """,
                boolean.class
        ).setParameter("tamagotchiId", tamagotchiId)
        .setParameter("newName", request.name())
        .getSingleResult();

        if (nameIsNotUnique) {
            throw new TamagotchiNameInvalidException("Tamagotchi name is not unique: " + request.name());
        }

        UUID pocketId = em.createQuery(
                "SELECT t.pocket.id AS id FROM Tamagotchi t WHERE t.id = :tamagotchiId",
                UUID.class
        )
        .setParameter("tamagotchiId", tamagotchiId)
        .getSingleResult();

        Pocket pocket = em.find(Pocket.class, pocketId);
        pocket.updateTamagotchi(tamagotchiId, request);
    }
}
```
Firstly, we check that any other Tamagotchi (aside from the one we want to update) already has the same name. 
If that’s true, we throw an exception. If you run the previous test again and check log, you’ll see that only COUNT query has been invoked:

```sql
select count(t1_0.id)>0
from tamagotchi t1_0
where t1_0.id!=? and t1_0.name=?
```
Anyway, I don’t recommend you to overuse this approach. You should treat it like an accurately pinned patch. 
In other words, put it only where it’s needed. Otherwise, 
I’d prefer relying on domain logic and leave code in services as simple as possible.