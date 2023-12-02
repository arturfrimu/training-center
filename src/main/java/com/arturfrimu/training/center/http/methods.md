## GET
**Utilizare:** "Te rog să-mi dai informațiile asociate acestei adrese URL."

**Scop**: Solicită reprezentarea unei resurse specifice. Cererile GET trebuie să fie doar pentru citire și fără efecte secundare.
## POST
**Utilizare**: "Iată niște date, te rog să le procesezi și să le păstrezi la această adresă URL."

**Scop**: Trimite date către server pentru a crea o nouă resursă. Datele sunt incluse în corpul cererii.
## PUT
**Utilizare**: "Iată niște date, te rog să înlocuiești resursa curentă cu acestea."

**Scop**: Înlocuiește toate reprezentările curente ale resursei țintă cu datele încărcate.
## DELETE
**Utilizare**: "Te rog să ștergi resursa care se află la această adresă URL."

**Scop**: Șterge resursa specificată.
## HEAD
**Utilizare**: "Te rog să-mi dai doar anteturile de răspuns pe care le-ai trimite pentru o cerere GET, dar fără corpul mesajului."

**Scop**: Similar cu GET, dar serverul nu returnează corpul în răspuns, doar anteturile.
## PATCH
**Utilizare**: "Iată niște modificări pentru resursa care se află la această adresă URL. Te rog să le aplici."

**Scop**: Aplică modificări parțiale la o resursă. Spre deosebire de PUT, PATCH aplică o schimbare parțială și nu înlocuiește întreaga resursă.
## OPTIONS
**Utilizare**: "Ce metode HTTP sunt suportate de această adresă URL?"

**Scop**: Descrie opțiunile de comunicare disponibile pentru resursa țintă.
## CONNECT
**Utilizare**: "Te rog să inițiezi o conexiune tunel către server."

**Scop**: Folosit de un client pentru a stabili o conexiune de tip tunel printr-un server proxy.
## TRACE
**Utilizare**: "Te rog să-mi retrimiți cererea exact așa cum ai primit-o pentru diagnosticare."

**Scop**: Efectuează un test de buclă de retur împreună cu calea către resursa țintă.
Explicație
Metodele HTTP definesc acțiunea pe care serverul trebuie să o efectueze pentru resursa specificată de către URL-ul cererii. Ele sunt o componentă esențială a arhitecturii REST și joacă un rol crucial în definirea naturii interacțiunii dintre client și server. Fiecare metodă are reguli specifice și efecte asupra stării resurselor serverului, fiind proiectată pentru a face HTTP un protocol flexibil și puternic pentru comunicarea în rețea.