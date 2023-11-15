### Integration testing
We use entities with a conjunction of repositories (Spring Data ones usually). 
Let’s write some use cases and test them:

* Create Pocket
* Create Tamagotchi
* Update Tamagotchi

The entire test suite is available by [this link](https://github.com/SimonHarmonicMinor/rich-domain-model-with-hibernate-example/blob/master/src/test/java/com/example/demo/service/PocketServiceIntegrationTest.java)

### Create Pocket

```java
@Service
@RequiredArgsConstructor
public class PocketService {

    private final EntityManager em;
    
    @Transactional
    public UUID createPocket(String name) {
        Pocket pocket = Pocket.newPocket(name);
        em.persist(pocket);
        return pocket.getId();
    }
}
    // Time to write some integration tests. Look at the code snippet below:

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Transactional(propagation = NOT_SUPPORTED)
@Import(PocketService.class)
class PocketServiceIntegrationTest {

    @Container
    @ServiceConnection
    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13");
    
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private PocketService pocketService;
    
    @BeforeEach
    void cleanDatabase() {
        // there is cascade constraint in the database deleting tamagotchis and deleted_tamagotchis
        transactionTemplate.executeWithoutResult(
        s -> em.getEntityManager().createQuery("DELETE FROM Pocket ").executeUpdate()
    );
}

    @Test
    void shouldCreateNewPocket() {
        UUID pocketId = pocketService.createPocket("New pocket");
    
        PocketDto dto = transactionTemplate.execute(
            s -> em.find(Pocket.class, pocketId).toDto()
        );
        assertEquals("New pocket", dto.name());
    }
}
```
I use Testcontainers library to start PosgtreSQL in Docker. 
Flyway migration tool creates tables before tests run.

You can check out the migrations by [this link.](https://github.com/SimonHarmonicMinor/rich-domain-model-with-hibernate-example/blob/master/src/main/resources/db/migration/V1__create_tables.sql)

I guess this snippet is not that complicated. So, let’s go next.

Create Tamagotchi
Look at the code service implementation below:

```java
@Service
@RequiredArgsConstructor
public class PocketService {
    /* other fields and methods */
    
    @Transactional
    public UUID createTamagotchi(UUID pocketId, TamagotchiCreateRequest request) {
        Pocket pocket = em.find(Pocket.class, pocketId);
        return pocket.createTamagotchi(request);
    }
}
```

As you can see, the Rich Domain Model pattern demands to declare services as thin layer 
that are easy to understand and test. And here is the test itself:

```java
/* same Java annotations */
class PocketServiceIntegrationTest {
/* initialization... */

@Test
void shouldCreateTamagotchi() {
    UUID pocketId = pocketService.createPocket("New pocket");

    UUID tamagotchiId = pocketService.createTamagotchi(
        pocketId,
        new TamagotchiCreateRequest("my tamagotchi", CREATED)
    );

    PocketDto dto = transactionTemplate.execute(
        s -> em.find(Pocket.class, pocketId).toDto()
    );
    assertThat(dto.tamagotchis())
        .anyMatch(t ->
            t.name().equals("my tamagotchi")
                && t.status().equals(CREATED)
                && t.id().equals(tamagotchiId)
        );
}
}
```
This one is a bit more interesting. 
Firstly, we create a Pocket and then add a Tamogotchi inside it. 
Assertions checks that expected Tamagotchi is present in result DTO.

### Update Tamagotchi
This one is the most intriguing. Check out the implementation below:

```java
@Service
@RequiredArgsConstructor
public class PocketService {
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
API demands to pass tamagotchiId. But the domain model allows us to update Tamagotchi only through Pocket because the latter is the aggregate root. So, we determine pocketId with additional query to DB and then select Pocket aggregate by its id. Test is also quite interesting:

```java
/* same Java annotations */
class PocketServiceIntegrationTest {
    /* other fields and methods */
    
    @Test
    void shouldUpdateTamagotchi() {
        UUID pocketId = pocketService.createPocket("New pocket");
        UUID tamagotchiId = pocketService.createTamagotchi(
        pocketId,
        new TamagotchiCreateRequest("my tamagotchi", CREATED)
    );
    
        pocketService.updateTamagotchi(
            tamagotchiId,
            new TamagotchiUpdateRequest("another tamagotchi", PENDING)
        );
    
        PocketDto dto = transactionTemplate.execute(
            s -> em.find(Pocket.class, pocketId).toDto()
        );
        assertThat(dto.tamagotchis())
            .anyMatch(t ->
                t.name().equals("another tamagotchi")
                    && t.status().equals(PENDING)
                    && t.id().equals(tamagotchiId)
            );
    }
}
```
* The steps are:
* Create Pocket.
* Create Tamagotchi.
* Update Tamagotchi.
* Validate the result DTO.

Nothing complicated, don’t you think?