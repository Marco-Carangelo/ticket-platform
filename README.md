# Ticket Platform
Piattaforma sviluppata tramite il framework Spring che consente di gestire le richieste inviate dagli utenti sotto forma di ticket.
Consiste in un utente admin che ha la facoltà di creare, modificare ed eliminare i ticket e di assegnarli a degli utenti con il ruolo operatore, che li gestiranno e ne aggiorneranno lo stato una volta completati.
Ogni ticket ha la sua pagina di dettaglio nella quale sia l'admin che l'operatore possono aggiungere delle note.
Gli operatori hanno la possibilità di visualizzare la propria pagina utente, dalla quale possono cambiare i propri dettagli ed impostare il loro profilo come non disponibile per l'assegnazione di nuovi ticket, ma solo quando non hanno altri ticket da completare in lista.

## Strumenti e framework utilizzati

* [Spring](https://spring.io/)
* [Hibernate ORM](https://hibernate.org/orm/)
* [MySql](https://www.mysql.com)
* [Bootstrap](https://getbootstrap.com/)
* [Maven](https://maven.apache.org/)

## Struttura del progetto

### Dashboard admin
![admin](https://github.com/user-attachments/assets/4b9d8f30-b4b0-4c59-bce3-412cd3ec892b)

La dashborda dell'admin è costituita da 4 elementi principali:
1. La lista di tutti i ticket presenti nel database.
2. Un campo per la ricerca dei ticket.
3. Il pulsante crea nuovo ticket.
4. Il pulsante crea nuovo operatore.

#### 1. Lista dei ticket
Si tratta di una tabella sulle cui colonne abbiamo l'indicazione, in ordine, dell'ID del ticket, della sua intestazione, della data di creazione e della data di ultima modifica.
Inoltre alla fine di ogni riga abbiamo tre pulsanti:
* _Show_. Per accedere alla pagina di dettaglio del ticket.
* _Edit_. Per accedere al form per modificare il ticket.
* _Delete_. Per modificare il ticket

#### 2. Barra di ricerca dei ticket per intestazione
Permette di inserire una stringa e di filtrare i ticket nella quale intestazione essa è contenuta.

#### 3. Pulsante crea nuovo ticket
Permette di accedere alla pagina contenente il form per creare un nuovo ticket.

#### 3. Pulsante crea nuovo operatore
Permette di accedere alla pagina contenente il form per creare un nuovo operatore.

