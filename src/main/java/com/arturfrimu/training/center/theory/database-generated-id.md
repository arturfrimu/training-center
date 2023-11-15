### Database generated ID
Previously I’ve mentioned that I’ll show you examples of client generated ID. 
However, sometimes we want to use other ID types. For example, sequence generated ones. 
Is this Rich Domain Model pattern also applicable to these ID types? 
It is, but there are also some concerns.

Firstly, have a look at Pocket and Tamagotchi entities using IDENTITY generation strategy:

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
@Setter(PRIVATE)
public class Pocket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /* other fields aren't important */

    public Long createTamagotchi(TamagotchiCreateRequest request) {
        Tamagotchi newTamagotchi = Tamagotchi.newTamagotchi(request.name(), request.status(), this);
        tamagotchis.add(newTamagotchi);
        validateTamagotchiNamesUniqueness();
// always returns null
        return newTamagotchi.getId();
    }

    /* other methods aren't important */

    public static Pocket newPocket(String name) {
        Pocket pocket = new Pocket();
        pocket.setName(name);
        pocket.createTamagotchi(new TamagotchiCreateRequest("Default", CREATED));
        return pocket;
    }
}

@Entity
@NoArgsConstructor(access = PROTECTED)
@Setter(PRIVATE)
@Getter
class Tamagotchi {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /* other fields and methods aren't important */

    public static Tamagotchi newTamagotchi(String name, Status status, Pocket pocket) {
        Tamagotchi tamagotchi = new Tamagotchi();
        tamagotchi.setName(name);
        tamagotchi.setPocket(pocket);
        tamagotchi.setStatus(status);
        return tamagotchi;
    }
}
```
As you can see, we don’t assign ID directly anymore. 
Instead, we leave the field with null value and let Hibernate fill it later. 
Unfortunately, this decision breaks the logic of Pocket.createTamagotchi method. 
We do not set ID during the creation of Tamagotchi object. 
So, the invocation of Tamagotchi.getId always returns null (until you flush changes to the database).

There are several ways to fix this issue.