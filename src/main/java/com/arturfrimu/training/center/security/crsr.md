**CSRF**, care este prescurtarea de la Cross-Site Request Forgery, este un tip de atac cibernetic care forțează un
utilizator final să execute acțiuni nedorite pe un site web pe care este autentificat deja. Acesta este un atac de tip
exploit al încrederii pe care un site o are în browserul utilizatorului.

# Iată câteva aspecte cheie despre CSRF:

## Funcționare

### Exploatarea Sesiunii:

Atacul CSRF se bazează pe faptul că browserul include automat cookie-uri de autentificare în cererile către un site web.

### Cereri Neașteptate:

Un atacator creează o cerere (de exemplu, un link, o imagine, un formular ascuns) care este trimisă de browserul
victimelor la un site web unde sunt autentificate, declanșând o acțiune nedorită.

## Exemple de Atacuri CSRF

### Modificări ale Contului:

Schimbarea adresei de e-mail sau a parolei utilizatorului pe un site.

### Tranzacții Financiare:

Autorizarea unei tranzacții bancare fără știrea utilizatorului.

### Postări pe Social Media:

Postarea de mesaje sau linkuri fără consimțământul utilizatorului.

## Prevenirea Atacurilor CSRF

### Token-uri Anti-CSRF:

Adăugarea unui token secret, unic pentru fiecare sesiune sau formular, care trebuie verificat de server la primirea
cererii.

### Verificarea Originii:

Verificarea headerelor HTTP Referer sau Origin pentru a asigura că cererea a fost trimisă de pe site-ul propriu.

### SameSite Cookie Attribute:

Setarea cookie-urilor cu atributul SameSite poate preveni trimiterea lor în cererile cross-site.

## Cele Mai Bune Practici

### Folosirea Framework-urilor Web Moderne:

Multe framework-uri web moderne au măsuri împotriva CSRF incluse.

## Atenție la Cookie-uri:

Configurarea corespunzătoare a cookie-urilor, inclusiv utilizarea atributelor Secure și HttpOnly.

### Educația Utilizatorilor:

Informarea utilizatorilor despre riscurile click-urilor pe linkuri necunoscute sau suspecte.

## Legătura cu Alte Atacuri

Este important de menționat că CSRF diferă de Cross-Site Scripting (XSS), unde un atacator injectează scripturi
malițioase într-o pagină web; în timp ce CSRF exploatează încrederea unui site în browserul utilizatorului, XSS
exploatează încrederea utilizatorului într-un anumit site web.

## Aspecte Legale și Compliance

Neglijarea protecției împotriva CSRF poate duce la încălcări ale reglementărilor privind protecția datelor și ale
confidențialității, cum ar fi GDPR.

## În concluzie,

CSRF este un atac serios care poate avea consecințe grave. Este esențial ca dezvoltatorii web să înțeleagă
și să implementeze măsuri de protecție adecvate pentru a asigura securitatea aplicațiilor web și a datelor
utilizatorilor.