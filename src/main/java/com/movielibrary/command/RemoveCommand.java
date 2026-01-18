package com.movielibrary.command;

import com.movielibrary.model.LibraryComponent;
import com.movielibrary.model.Movie;
import com.movielibrary.model.MovieCollection;
import com.movielibrary.ui.ConsoleUI;

/**
 * Comando per rimuovere un film dalla libreria.
 * L'utente specifica l'indice del film da rimuovere.
 */
public class RemoveCommand implements Command {
    private final ConsoleUI ui;

    /**
     * Costruisce un nuovo comando RemoveCommand.
     *
     * @param ui L'interfaccia utente per interagire con la libreria.
     */
    public RemoveCommand(ConsoleUI ui) {
        this.ui = ui;
    }

    /**
     * Esegue il comando di rimozione.
     *
     * @param args Gli argomenti del comando, dove il primo argomento è l'indice del film da rimuovere.
     * @return Un messaggio che indica l'esito della rimozione.
     */
    @Override
    public String execute(String... args) {
        if (args.length < 1) {
            return "Errore: Specifica il numero del film da rimuovere. Esempio: remove 1";
        }

        MovieCollection library = ui.getLibrary();
        if (library.getMovieCount() == 0) {
            return "La libreria è già vuota.";
        }

        try {
            int movieIndex = Integer.parseInt(args[0]);
            LibraryComponent removedComponent = library.removeComponentByIndex(movieIndex);

            if (removedComponent instanceof Movie) {
                return String.format("'%s' rimosso dalla libreria.", ((Movie) removedComponent).getTitle());
            } else if (removedComponent != null) {
                return "Componente rimosso (era una collezione).";
            } else {
                return "Numero non valido. Seleziona un numero dalla lista mostrata con 'show'.";
            }
        } catch (NumberFormatException e) {
            return "Errore: Inserisci un numero valido.";
        }
    }
}
