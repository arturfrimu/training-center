### Unit testing entities
We’re going to test these scenarios:

Pocket must always possess at least one Tamagotchi.
If you delete Tamagotchi, you can restore it by name.
If you delete multiple Tamagotchi with the same name, you can only restore the last one.
The entire test suite is available by this link.

Pocket must always possess at least one Tamagotchi
Look at the unit tests below.

```java
class PocketTest {

@Test
void shouldCreatePocketWithTamagotchi() {
Pocket pocket = Pocket.newPocket("My pocket");

    PocketDto dto = pocket.toDto();

    assertEquals(1, dto.tamagotchis().size());
}

@Test
void shouldForbidDeletionOfASingleTamagotchi() {
Pocket pocket = Pocket.newPocket("My pocket");
PocketDto dto = pocket.toDto();
UUID tamagotchiId = dto.tamagotchis().get(0).id();

    assertThrows(
        TamagotchiDeleteException.class,
        () -> pocket.deleteTamagotchi(tamagotchiId)
    );
}
}
```
The first one checks that Pocket is being created with a single Tamagotchi. 
Whilst the second one validates that you cannot delete Tamagotchi if it’s a single one.

What I like about those tests is that they are unit ones. 
No database, no Testcontainers, just regular JUnit and we’ve validated business logic successfully. 
Cool! Let’s move forward.

If you delete Tamagotchi, you can restore it by name
This one is a bit more complicated. Look at the code example below.

```java
class PocketTest {

@Test
void shouldDeleteTamagotchiById() {
Pocket pocket = Pocket.newPocket("My pocket");
UUID tamagotchiId = pocket.createTamagotchi(new TamagotchiCreateRequest("My tamagotchi", CREATED));

    pocket.deleteTamagotchi(tamagotchiId);

    PocketDto dto = pocket.toDto();
    assertThat(dto.tamagotchis())
        .noneMatch(t -> t.name().equals("My tamagotchi"));
}

@Test
void shouldRestoreTamagotchiById() {
Pocket pocket = Pocket.newPocket("My pocket");
UUID tamagotchiId = pocket.createTamagotchi(new TamagotchiCreateRequest("My tamagotchi", CREATED));
pocket.deleteTamagotchi(tamagotchiId);

    pocket.restoreTamagotchi("My tamagotchi");

    PocketDto dto = pocket.toDto();
    assertThat(dto.tamagotchis())
        .anyMatch(t -> t.name().equals("My tamagotchi"));
}
}
```
The shouldDeleteTamagotchiById checks that deletion works as expected. 
The other one validates restoreTamagotchi method behaviour.

If you delete multiple Tamagotchi with the same name, you can only restore the last one
This one is the most challenging. Look at the code example below.

```java
class PocketTest {

@Test
void shouldRestoreTheLastTamagotchi() {
Pocket pocket = Pocket.newPocket("My pocket");
UUID tamagotchiId = pocket.createTamagotchi(new TamagotchiCreateRequest("My tamagotchi", CREATED));
pocket.deleteTamagotchi(tamagotchiId);
tamagotchiId = pocket.createTamagotchi(new TamagotchiCreateRequest("My tamagotchi", PENDING));
pocket.deleteTamagotchi(tamagotchiId);

    pocket.restoreTamagotchi("My tamagotchi");

    PocketDto dto = pocket.toDto();
    assertThat(dto.tamagotchis())
        .anyMatch(t ->
            t.name().equals("My tamagotchi")
            && t.status().equals(PENDING)
        );
}
}
```
Here we do these steps:

Create Pocket.
Create Tamagotchi with name of My tamagotchi and status CREATED.
Delete Tamagotchi.
Create Tamagotchi with name of My tamagotchi and status PENDING.
Restore Tamagotchi by name My tamagotchi.
Verify that the last one Tamagotchi has been restored (with status of PENDING).

**Rich Domain Model pattern allows us to test complex business scenarios** with simple unit tests. 
I think it’s outstanding. 
However, integration tests are also important because we need to store data in DB not in RAM. 
Let’s discuss this part of the equation.

