Current result
We’ve already discussed
We’ve already discussed three points:

No-args constructor.
Setters.
Getters.
If we remove those pieces, the code will look like this:

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Pocket {
@Id
private UUID id;

private String name;

@OneToMany(mappedBy = "pocket")
private List<Tamagotchi> tamagotchis = new ArrayList<>();

public static Pocket newPocket(String name) {
Pocket pocket = new Pocket();
pocket.setId(UUID.randomUUID());
pocket.setName(name);
return pocket;
}
}

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Tamagotchi {
@Id
private UUID id;

private String name;

@ManyToOne(fetch = LAZY)
@JoinColumn(name = "pocket_id")
private Pocket pocket;

@Enumerated(STRING)
private Status status;

public void changeName(String name) {
if (status == PENDING) {
throw new TamagotchiStatusException("Tamagotchi cannot be modified because it's PENDING");
}
if (!nameIsValid(name)) {
throw new TamagotchiNameInvalidException("Invalid Tamagotchi name: " + name);
}
this.name = name;
}

public static Tamagotchi newTamagotchi(String name, Pocket pocket) {
Tamagotchi tamagotchi = new Tamagotchi();
tamagotchi.setId(UUID.randomUUID());
tamagotchi.setName(name);
tamagotchi.setPocket(pocket);
tamagotchi.setStatus(CREATED);
return tamagotchi;
}
}
```