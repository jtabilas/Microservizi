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


![ms drawio (1)](https://github.com/jtabilas/Microservizi/assets/94932581/223a7ebc-22d2-4141-9add-66a6968790d1)
