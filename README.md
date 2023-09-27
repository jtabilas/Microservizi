# Architettura a Microservizi con Spring Boot

In questo progetto è stata creata un'architettura a microservizi utilizzando il framework Spring Boot. L'obiettivo principale del progetto è quello di dimostrare come suddividere le diverse funzionalità di un'applicazione in microservizi autonomi, ciascuno dei quali gestisce una parte specifica del dominio.

## Microservizi

L'architettura è composta da quattro microservizi distinti:

1. **Account Service**:
   - Responsabile della gestione degli account utente.
   - Fornisce funzionalità di registrazione, accesso e gestione profilo utente.

2. **Product Catalog**:
   - Gestisce il catalogo dei prodotti offerti dall'azienda.
   - Fornisce informazioni sui prodotti, prezzi e disponibilità.

3. **Cart Server**:
   - Gestisce il carrello degli acquisti dell'utente.
   - Permette agli utenti di aggiungere, rimuovere e modificare gli elementi nel carrello.

4. **Order Server**:
   - Gestisce l'elaborazione degli ordini.
   - Gestisce la creazione, l'aggiornamento e la conferma degli ordini effettuati dagli utenti.

## API Gateway

Tutte le chiamate di ingresso al sistema vengono gestite attraverso un API Gateway. L'API Gateway agisce come intermediario tra i client e i vari microservizi, semplificando l'accesso e offrendo funzionalità trasversali.

## Architettura dei ms

![ms drawio (1)](https://github.com/jtabilas/Microservizi/assets/94932581/cb78f9be-28de-4dcb-8ff1-673de4d6c1ec)

## Architettura del db 

![db-schema](https://github.com/jtabilas/Microservizi/assets/94932581/de248d4b-aed3-4335-92cd-4a58acb1c5e3)

## Funzionamento del Account Service 

   -   Aggiungere i dati di un utente mediante un RequestBody attraverso  un post nell'indirizzo "http://localhost:8080/api/addAccount".

![1](https://github.com/jtabilas/Microservizi/assets/94932581/b457c238-e861-4add-bc60-5bcc2de772ca)

   -   Check di tutti gli utenti attraverso un get nell'indirizzo "http://localhost:8080/api/accounts".
   
![2](https://github.com/jtabilas/Microservizi/assets/94932581/67776dcc-07ed-4fce-9397-170f452b41b2)

   -   Login con dati corretti nell'indirizzo "http://localhost:8080/api/login"   

![3](https://github.com/jtabilas/Microservizi/assets/94932581/966e27b4-e067-49ba-86aa-95e3032695b2)

   -   Login con dati errati nell'indirizzo "http://localhost:8080/api/login"

![4](https://github.com/jtabilas/Microservizi/assets/94932581/c9577e82-970e-43dd-b688-82f7c58acd06)
