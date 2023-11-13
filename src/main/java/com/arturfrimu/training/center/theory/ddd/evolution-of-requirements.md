Evolution of requirements
As I said
As I said before, entities aren’t static. Requirements change and invariants as well. So, let’s look through a hypothetical process of implementing new requirements and see how it goes.

Each Pocket must possess at least one Tamagotchi
It means that we should create a Tamagotchi, when a new Pocket is instantiated. Also, if you want to delete a Tamagotchi, you have to check that it’s not the single one within the Pocket. Look at the code example below:

```java

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Pocket {
    /* fields and other methods */

    public void deleteTamagotchi(UUID tamagotchiId) {
        Tamagotchi tamagotchi = tamagotchiById(tamagotchiId);
        if (tamagothis.size() == 1) {
            throw new TamagotchiDeleteException("Cannot delete Tamagotchi because it's the single one");
        }
        tamagotchis.remove(tamagotchi);
    }

    public static Pocket newPocket(String name) {
        Pocket pocket = new Pocket();
        pocket.setId(UUID.randomUUID());
        pocket.setName(name);
        pocket.createTamagotchi(new TamagotchiCreateRequest("Default")); // creating default tamagotchi
        return pocket;
    }
}
```
As you can see, invariants’ correctness is guaranteed within an aggregate. Even if you want to, you cannot create a Pocket with zero Tamagotchi or delete Tamagotchi if it’s a single one. And I think that it’s great. Code becomes less error-prone and easier to maintain.

Every Tamagotchi name has to be unique within a Pocket
To implement this requirement, we need to alter createTamagotchi and updateTamagotchi methods a bit. Look at the code example below:

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Pocket {
    /* fields and other methods */
    
    public UUID createTamagotchi(TamagotchiCreateRequest request) {
        Tamagotchi tamagotchi = Tamagotchi.newTamagotchi(request.name(), this);
        tamagotchis.add(tamagotchi);
        validateTamagotchiNamesUniqueness();
        return tamagotchi.getId();
    }
    
    public void updateTamagotchi(UUID tamagotchiId, TamagotchiUpdateRequest request) {
        Tamagotchi tamagotchi = tamagotchiById(tamagotchiId);
        tamagotchi.changeName(request.name());
        validateTamagotchiNamesUniqueness();
    }

    private void validateTamagotchiNamesUniqueness() {
        Set<String> names = new HashSet<>();
        for (Tamagotchi tamagotchi : tamagotchis) {
            if (!names.add(tamagotchi.getName()) {
                throw new TamagotchiNameInvalidException("Tamagotchi name is not unique: " + tamagotchi.getName());
            }
        }
    }
}
```
You’ve probably noticed that I added a getter for Tamagotchi.name field. Because Pocket and Tamagotchi form a single aggregate, it’s fine to provide getters. Because Pocket might need this information. However, Tamagotchi should not request anything from Pocket. It’s also better to mark this getter as package-private. So, no one can access it outside of the package.

I understand that validateTamagotchiNamesUniqueness doesn't perform well. Don't worry, we'll discuss workarounds later in the Performance implications section.

Once again, the domain model guarantees that each Tamagotchi name is unique within a Pocket. What is interesting is that API hasn’t changed a bit. The code that invokes those public methods (likely domain services) doesn’t have to change logic.

If a user deletes a Tamagotchi, they can restore it by name
This one is tricky and involves soft deletion. It also has additional points:

If Tamagotchi with the same name already exists, a user cannot restore the one they've deleted.
If a user deletes multiple Tamagotchis with the same name, they can only restore the last one.
I’m not a fan of soft deletion that involves adding isDeleted column by many reasons. Instead, I will introduce a new entity DeletedTamagotchi that contains the state of deleted Tamagotchi. Look at the code example below.

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
class DeletedTamagotchi {
    @Id
    private UUID id;
    
    private String name;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pocket_id")
    private Pocket pocket;
    
    @Enumerated(STRING)
    private Status status;
    
    public static DeletedTamagotchi newDeletedTamagotchi(Tamagotchi tamagotchi) {
        DeletedTamagotchi deletedTamagotchi = new DeletedTamagotchi();
        deletedTamagotchi.setId(UUID.randomUUID());
        deletedTamagotchi.setName(tamagotchi.getName());
        deletedTamagotchi.setPocket(tamagotchi.getPocket());
        deletedTamagotchi.setStatus(tamagotchi.getStatus());
        return deletedTamagotchi;
    }
}
```
Tamagotchi entity is rather simple, so DeletedTamagotchi contains the same fields. However, if the original entity were more complicated, it couldn’t be the case. For example, you could save the state of Tamagotchi in Map<String, Object> fields that transforms to JSONB in the database.

Also, DeletedTamagotchi entity is package-private like Tamagotchi. So, the presence of this entity is an implementation detail. The other parts of the code don’t need to know this and interact with DeletedTamagotchi directly. Instead, it’s better to provide a single method Pocket.restoreTamagotchi without additional details.

Now let’s alter Pocket entity to the new requirements. Look at the code example below:

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Pocket {
/* fields and other methods */

@OneToMany(mappedBy = "pocket", cascade = PERSIST, orphanRemoval = true)
private List<DeletedTamagotchi> deletedTamagotchis = new ArrayList<>();
    
    public void deleteTamagotchi(UUID tamagotchiId) {
        Tamagotchi tamagotchi = tamagotchiById(tamagotchiId);
        if (tamagothis.size() == 1) {
            throw new TamagotchiDeleteException("Cannot delete Tamagotchi because it's the single one");
        }
        tamagotchis.remove(tamagotchi);
        addDeletedTamagotchi(tamagotchi);
    }
    
    private void addDeletedTamagotchi(Tamagotchi tamagotchi) {
        Iterator<DeletedTamagotchi> iterator =  deletedTamagotchis.iterator();
        // if Tamagotchi with the same has been deleted,
        // remove information about it
        while (iterator.hasNext()) {
            DeletedTamagotchi deletedTamagotchi = iterator.next();
            if (deletedTamagotchi.getName().equals(tamagotchi.getName()) {
                iterator.remove();
                break;
            }
         }
        deletedTamagotchis.add(
        newDeletedTamagotchi(tamagotchi)
    );
    }
    
    public UUID restoreTamagotchi(String name) {
        DeletedTamagotchi deletedTamagotchi = deletedTamagotchiByName(name);
        return createTamagotchi(new TamagotchiCreateRequest(deletedTamagotchi.getName()));
    }
}
```
The deleteTamagotchi method also creates or replaces a DeletedTamagotchi record. Meaning that every other code that calls the method for whatever reason doesn't violate the new requirement about soft deletion because it's been implemented internally.

To perform the required business operation, you should just invoke Pocket.restoreTamagotchi. We hid all the complex details behind the scenes. What’s even better is that DeletedTamagotchi is not a part of public API. Meaning that it can be easily modified or even removed, if it’s not needed anymore.

As you can see, placing business logic within an aggregate has significant benefits. However, it’s not the end of the story. There are still some concerns we need to deal with. And the next one is querying data.