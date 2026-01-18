# MovieLibrary

MovieLibrary è un'applicazione per la gestione di una libreria di film. Permette di aggiungere, modificare, eliminare e visualizzare film, oltre a ricercare titoli specifici.

## Funzionalità principali

- **Aggiunta film**: Inserisci nuovi film specificando titolo, anno, genere, regista e altri dettagli.
- **Visualizzazione elenco**: Consulta la lista completa dei film presenti in libreria.
- **Ricerca**: Trova rapidamente film tramite titolo, genere o altri criteri.
- **Modifica**: Aggiorna le informazioni di un film già inserito.
- **Eliminazione**: Rimuovi film dalla libreria.
- **Salvataggio dati**: I dati vengono salvati in modo persistente (es. file locale o database).

## Come funziona

1. **Avvio**: Avvia il programma tramite il comando appropriato (es. `python main.py`).
2. **Menu principale**: Scegli l'operazione desiderata dal menu interattivo.
3. **Gestione film**: Segui le istruzioni a schermo per aggiungere, modificare, cercare o eliminare film.
4. **Chiusura**: Alla chiusura, i dati vengono salvati automaticamente.

## Requisiti

- Python 3.x
- Eventuali librerie aggiuntive (vedi `requirements.txt`)

## Esempio di utilizzo

```bash
$ python main.py
Benvenuto in MovieLibrary!
1. Aggiungi film
2. Visualizza tutti i film
3. Cerca film
4. Modifica film
5. Elimina film
6. Esci
Seleziona un'opzione:
```

## Struttura del progetto

- `main.py`: punto di ingresso dell'applicazione
- `movie.py`: definizione della classe Film
- `library.py`: gestione della libreria e delle operazioni CRUD
- `README.md`: questo file

## Autore

- [Il tuo nome]
