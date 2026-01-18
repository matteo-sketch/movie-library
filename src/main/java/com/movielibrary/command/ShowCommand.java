package com.movielibrary.command;

import com.movielibrary.model.LibraryComponent;
import com.movielibrary.model.MovieCollection;
import com.movielibrary.ui.ConsoleUI;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Comando per visualizzare i film presenti nella libreria.
 * Formatta l'elenco dei film in una stringa leggibile per l'utente.
 */
public class ShowCommand implements Command {
    private final ConsoleUI ui;

    /**
     * Costruisce un nuovo comando ShowCommand.
     *
     * @param ui L'interfaccia utente da cui ottenere la libreria da visualizzare.
     */
    public ShowCommand(ConsoleUI ui) {
        this.ui = ui;
    }

    /**
     * Esegue il comando per visualizzare la libreria.
     *
     * @param args Argomenti non utilizzati per questo comando.
     * @return Una stringa con l'elenco dei film nella libreria.
     */
    @Override
    public String execute(String... args) {
        MovieCollection library = ui.getLibrary();
        if (library.getMovieCount() == 0) {
            return "La tua libreria è vuota.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(library.getName()).append(" ---\n");
        List<LibraryComponent> components = library.getComponents();
        AtomicInteger index = new AtomicInteger(1);

        if (components.isEmpty()) {
            sb.append("Nessun film nella collezione principale.\n");
        } else {
            components.forEach(component -> {
                if (component instanceof com.movielibrary.model.Movie) {
                    sb.append(String.format("%d: %s\n", index.getAndIncrement(), component.display("")));
                }
            });
        }
        sb.append("---------------------\n");
        sb.append("Usa 'remove <numero>' per eliminare un film.");
        return sb.toString();
    }
}
