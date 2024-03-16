# API

/teams - GET: Retrieve all football teams.

/team/{id} - GET: Retrieve a specific football team by its ID.

/teams - POST: Add a new football team.

/team/{id} - PUT: Update details of a specific football team.

/team/{id} - DELETE: Remove a football team.

/teams/{teamId}/players - GET: Retrieve all players belonging to a specific team.

/players - GET: Retrieve all players.

/player/{id} - GET: Retrieve a specific player by their ID.

/players - POST: Add a new player to a team.

/player/{id} - PUT: Update details of a specific player.

/player/{id} - DELETE: Remove a player from a team.

/matches - GET: Retrieve all football matches.

/match/{id} - GET: Retrieve details of a specific match by its ID.

/matches/upcoming - GET: Retrieve all upcoming football matches.

/matches/results - GET: Retrieve results of past football matches.

/matches - POST: Schedule a new football match.

/match/{id} - PUT: Update details of a specific football match.

/match/{id} - DELETE: Cancel a scheduled football match.

# Structura requesturilor

# Team
1. /teams - GET
Descriere: Returnează toate echipele de fotbal.
Cod de status: 200 OK
Structura JSON în răspuns:
```json
[
  {
    "id": 1,
    "name": "Echipa A",
    "founded": "1901",
    "stadium": "Stadion A"
  },
  {
    "id": 2,
    "name": "Echipa B",
    "founded": "1902",
    "stadium": "Stadion B"
  }
]
```


2. /team/{id} - GET
Descriere: Returnează o echipă specifică de fotbal pe baza ID-ului.
Cod de status: 200 OK
Structura JSON în răspuns:
```json
{
  "id": 1,
  "name": "Echipa A",
  "founded": "1901",
  "stadium": "Stadion A"
}
```

3. /teams - POST

Descriere: Adaugă o nouă echipă de fotbal.
Cod de status: 201 Created
Body cerere:

```json
{
  "name": "Echipa Nouă",
  "founded": "1920",
  "stadium": "Stadion Nou"
}
```


4. /team/{id} - PUT
Descriere: Actualizează detaliile unei echipe specifice de fotbal pe baza ID-ului său.

Cod de status HTTP: 200 OK pentru actualizare reușită, 404 Not Found dacă echipa cu ID-ul specificat nu există.

Structura corpului cererii:

```json
{
  "name": "Nume Echipă Actualizat",
  "founded": "Anul Fondării",
  "stadium": "Nume Stadion Actualizat"
}
```

Structura corpului răspunsului:

```json
{
  "id": "ID-ul echipei actualizate",
  "name": "Nume Echipă Actualizat",
  "founded": "Anul Fondării",
  "stadium": "Nume Stadion Actualizat"
}
```

5. /team/{id} - DELETE
Descriere: Elimină o echipă de fotbal specifică pe baza ID-ului său.

Cod de status HTTP: 204 No Content la ștergere reușită, 404 Not Found dacă echipa cu ID-ul specificat nu există.

Răspuns: Această cerere, de obicei, nu are un corp al răspunsului. Codul de status indică rezultatul operațiunii.

6. /teams/{teamId}/players - GET
Descriere: Returnează toți jucătorii care aparțin unei echipe specifice de fotbal.

Cod de status HTTP: 200 OK

Structura corpului răspunsului:

```json
[
  {
    "id": 1,
    "name": "Nume Jucător 1",
    "position": "Poziția Jucătorului",
    "number": "Numărul Jucătorului",
    "teamId": "ID-ul echipei din care face parte jucătorul"
  },
  {
    "id": 2,
    "name": "Nume Jucător 2",
    "position": "Poziția Jucătorului",
    "number": "Numărul Jucătorului",
    "teamId": "ID-ul echipei din care face parte jucătorul"
  }
]
```


# Player
1. /players - GET
   Descriere: Returnează toți jucătorii.

Cod de status HTTP: 200 OK

Structura corpului răspunsului:

```json
[
{
"id": 1,
"name": "Nume Jucător 1",
"position": "Poziția Jucătorului",
"teamId": "ID-ul echipei"
},
{
"id": 2,
"name": "Nume Jucător 2",
"position": "Poziția Jucătorului",
"teamId": "ID-ul echipei"
}
]
```


2. /player/{id} - GET
   Descriere: Returnează un jucător specific pe baza ID-ului său.

Cod de status HTTP: 200 OK

Structura corpului răspunsului:

```json
{
"id": "ID-ul jucătorului",
"name": "Nume Jucător",
"position": "Poziția Jucătorului",
"teamId": "ID-ul echipei din care face parte jucătorul"
}
```

3. /players - POST
   Descriere: Adaugă un nou jucător unei echipe.

Cod de status HTTP: 201 Created

Structura corpului cererii:

```json
{
"name": "Nume Jucător Nou",
"position": "Poziția Jucătorului",
"teamId": "ID-ul echipei la care va fi adăugat"
}
```


4. /player/{id} - PUT
   Descriere: Actualizează detaliile unui jucător specific pe baza ID-ului său.

Cod de status HTTP: 200 OK pentru actualizare reușită, 404 Not Found dacă jucătorul cu ID-ul specificat nu există.

Structura corpului cererii:

```json
{
"name": "Nume Jucător Actualizat",
"position": "Poziția Actualizată",
"teamId": "ID-ul echipei actualizate"
}
```
Structura corpului răspunsului:

```json
{
"id": "ID-ul jucătorului actualizat",
"name": "Nume Jucător Actualizat",
"position": "Poziția Actualizată",
"teamId": "ID-ul echipei actualizate"
}
```


5. /player/{id} - DELETE
   Descriere: Elimină un jucător specific pe baza ID-ului său din echipă.

Cod de status HTTP: 204 No Content la ștergere reușită, 404 Not Found dacă jucătorul cu ID-ul specificat nu există.

Răspuns: Această cerere, de obicei, nu are un corp al răspunsului. Codul de status indică rezultatul operațiunii.

