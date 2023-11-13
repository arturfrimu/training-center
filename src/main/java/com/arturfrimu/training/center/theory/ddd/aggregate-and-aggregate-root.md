Aggregate and Aggregate root
Previously I’ve mentioned the Aggregate pattern. Speaking about our domain, the Pocket entity should be the Aggregate root. However, existing API allows us to access Tamagotchi entity directly. Let’s fix that.

Firstly, let’s add simple CREATE/UPDATE/DELETE operations. Look at the code example below:

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Pocket {
@Id
private UUID id;

private String name;

@OneToMany(mappedBy = "pocket", cascade = PERSIST, orphanRemoval = true)
private List<Tamagotchi> tamagotchis = new ArrayList<>();

public UUID createTamagotchi(TamagotchiCreateRequest request) {
Tamagotchi tamagotchi = Tamagotchi.newTamagotchi(request.name(), this);
tamagotchis.add(tamagotchi);
return tamagotchi.getId();
}

public void updateTamagotchi(UUID tamagotchiId, TamagotchiUpdateRequest request) {
Tamagotchi tamagotchi = tamagotchiById(tamagotchiId);
tamagotchi.changeName(request.name());
}

public void deleteTamagotchi(UUID tamagotchiId) {
Tamagotchi tamagotchi = tamagotchiById(tamagotchiId);
tamagotchis.remove(tamagotchi);
}

private Tamagotchi tamagotchiById(UUID tamagotchiId) {
return tamagotchis
.stream()
.filter(t -> t.getId().equals(tamagotchiId))
.findFirst()
.orElseThrow(() -> new TamagotchiNotFoundException("Cannot find Tamagotchi by ID=" + tamagotchiId));
}

public static Pocket newPocket(String name) {
Pocket pocket = new Pocket();
pocket.setId(UUID.randomUUID());
pocket.setName(name);
return pocket;
}
}

@Entity
@NoArgsConstructor(access = PROTECTED)
class Tamagotchi {
@Id
@Getter
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
There are a lot of nuances. So, I’ll point out each of them one by one. Firstly, Pocket entity provides methods createTamagotchi, updateTamagotchi, and deleteTamagotchi as-is. You don’t retrieve any information from Tamagotchi or Pocket. You just invoke the required functionality.

I’m aware that such a technique also has performance penalties. We’ll also discuss some approaches to overcome these problems later.

Then goes Tamagotchi entity. The first thing I want you to notice is that the entity is package-private. Meaning that nobody can access Tamagotchi outside of the package. Therefore, calling Pocket directly is the only way.

Now you may think that its profit isn’t so obvious. But soon we’ll discuss the evolution of aggregate and you’ll see the benefits.

Neither Pocket nor Tamagotchi entity provides regular setters or getters. One can only invoke public methods of Pocket entity.