### Manually fill the id
You can eliminate @GeneratedValue annotation usage and pass the ID 
value directly in the constructor. 
In this case, you have to invoke SELECT nextval('mysequence') statement and pass its 
result to an entity. Look at the code example below:

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
@Setter(PRIVATE)
public class Pocket {
    @Id
    private Long id;

    /* other fields aren't important */

    public Long createTamagotchi(long tamagotchiId, TamagotchiCreateRequest request) {
        Tamagotchi newTamagotchi = Tamagotchi.newTamagotchi(tamagotchiId, request.name(), request.status(), this);
        tamagotchis.add(newTamagotchi);
        validateTamagotchiNamesUniqueness();
// always returns null
        return newTamagotchi.getId();
    }

    /* other methods aren't important */

    public static Pocket newPocket(long id, String name) {
        Pocket pocket = new Pocket();
        pocket.setId(id);
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
    private Long id;

    /* other fields and methods aren't important */

    public static Tamagotchi newTamagotchi(long id, String name, Status status, Pocket pocket) {
        Tamagotchi tamagotchi = new Tamagotchi();
        tamagotchi.setId(id);
        tamagotchi.setName(name);
        tamagotchi.setPocket(pocket);
        tamagotchi.setStatus(status);
        return tamagotchi;
    }
}
```
The advantage is that your entity classes do not depend on some 
Hibernate magic and you can still validate business cases with regular unit tests. 
But you also make your code more verbose. Because you to have pass IDs manually.

Anyway, this approach is worth considering.

I found this option in [this article](https://www.stemlaur.com/blog/2021/03/30/tech-hibern-hate/) 
Actually, the author demands to stop use Hibernate at all.
Even though I like Hibernate, I found some arguments intriguing.