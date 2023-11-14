### Querying data
When we deal with Hibernate, usually we use public getters to transform entity into DTO and return it to the user. 
However, only Pocket entity is public now, and it doesn’t provide any getters (aside from Pocket.getId()). 
How do we perform queries in this case? I can suggest several approaches.

### Manual queries
The obvious solution is just writing regular JPQL or SQL statements. 
Hibernate uses reflection and doesn’t demand public getters for fields. 
This may work if you start a project from scratch.
But if you already relying on getters to retrieve information from the entity and put it into DTO, then transition might be overwhelming. 
That’s why we have a second option.

### Introducing toDto method
An entity can provide toDto or similar method that returns its internal representation as a separate data structure. 
It’s similar to Memento design pattern. Look at the code example below:

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
@Setter(PRIVATE)
public class Pocket {
/* other fields and methods */

    public PocketDto toDto() {
        return new PocketDto(
                id,
                name,
                tamagotchis.stream()
                        .map(Tamagotchi::toDto)
                        .toList()
        );
    }
}

@Entity
@NoArgsConstructor(access = PROTECTED)
@Setter(PRIVATE)
@Getter
class Tamagotchi {
/* other fields and methods */

    public TamagotchiDto toDto() {
        return new TamagotchiDto(id, name, status);
    }
}
```
The returned DTO is an immutable object that couldn’t affect entities’ state. 
Besides, the approach is also helpful for unit testing. Let’s move on to this part.