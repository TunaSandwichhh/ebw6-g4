# CRM per Compagnia Energetica

Questo progetto è un sistema CRM (Customer Relationship Management) per una compagnia energetica. Il CRM consente di tenere traccia dei clienti e delle fatture, garantendo una gestione efficiente e il recupero dei dati.

### Funzionalità
- **Gestione Clienti:** Aggiunta, aggiornamento e cancellazione delle informazioni dei clienti.
- **Gestione Fatture:** Tracciamento delle fatture, dei loro stati e delle informazioni associate ai clienti.
- **Gestione Indirizzi:** Gestione degli indirizzi collegati ai clienti.
- **Autenticazione e Autorizzazione Utente:** Accesso sicuro con ruoli e permessi.
  
### Entità
  - **Indirizzo:** Rappresenta l'indirizzo di un cliente.
  - **Contea:** Rappresenta le informazioni sulla contea.
  - **Provincia:** Rappresenta le informazioni sulla provincia.
  - **Cliente:** Rappresenta le informazioni del cliente.
  - **Fattura:** Rappresenta i dettagli della fattura.
  - **Utente:** Rappresenta i dipendenti che utilizzano il CRM.

### Endpoint API

Ottenere una lista di entities

`GET /api/:entity`

Ottenere entity tramite ID

`GET /api/:entity/{id}`

Creare una nuova entity

`POST /api/:entity`

Aggiornare un' entity

`PATCH /api/:entity/{id}`

Cancellare un indirizzo

`DELETE /api/:entity/{id}`

### Collaboratori

[Milo Zaffiri](https://github.com/Zaffirim)</br>
[Fabio Gallingani](https://github.com/fabiogalli95)</br>
[Clarissa Piovesan](https://github.com/clarissa1110)</br>
[Elias Pinna](https://github.com/PinnaElias)</br>
[Paola Del Vecchio](https://github.com/smoulderpipe)</br>
[Davide Faggionato](https://github.com/TunaSandwichhh)</br>