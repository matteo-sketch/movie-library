package com.movielibrary.ui;

import com.movielibrary.api.TmdbApiClient;
import com.movielibrary.command.*;
import com.movielibrary.dto.MovieDto;
import com.movielibrary.factory.MovieFactory;
import com.movielibrary.model.MovieCollection;
import com.movielibrary.service.LibraryStorageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Gestisce l'interfaccia utente a riga di comando (CLI) per l'applicazione.
 * Si occupa del ciclo di input, dell'esecuzione dei comandi e della visualizzazione dell'output.
 */
public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Command> commands = new HashMap<>();
    private MovieCollection library = new MovieCollection("La Mia Libreria");
    private List<MovieDto> lastSearchResults = new ArrayList<>();
    private String lastCommandOutput = "Benvenuto in Movie Library!";
    private boolean running = true;
    private final ExecutorService commandExecutor = Executors.newSingleThreadExecutor();

    /**
     * Costruisce una nuova interfaccia utente, inizializzando tutti i comandi disponibili.
     */
    public ConsoleUI() {
        TmdbApiClient apiClient = new TmdbApiClient();
        MovieFactory movieFactory = new MovieFactory();
        LibraryStorageService storageService = new LibraryStorageService("movielibrary.ser");

        commands.put("cerca", new SearchCommand(apiClient, this));
        commands.put("aggiungi", new AddCommand(this, movieFactory));
        commands.put("togli", new RemoveCommand(this));
        commands.put("vedi", new ShowCommand(this));
        commands.put("salva", new SaveCommand(storageService, this));
        commands.put("carica", new LoadCommand(storageService, this));
        commands.put("aiuto", new HelpCommand());
        commands.put("esci", new ExitCommand(this));
    }

    /**
     * Avvia il ciclo principale dell'applicazione, leggendo l'input dell'utente
     * e eseguendo i comandi corrispondenti.
     */
    public void run() {
        lastCommandOutput = commands.get("carica").execute();

        while (running) {
            displayDashboard();
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+", 2);
            String commandName = parts[0].toLowerCase();
            String[] args = (parts.length > 1) ? parts[1].split("\\s+") : new String[0];
            Command command = commands.get(commandName);

            if (command == null) {
                lastCommandOutput = "Comando non riconosciuto.";
                continue;
            }

            try {
                Future<String> futureResult = commandExecutor.submit(() -> command.execute(args));
                System.out.print("Elaborazione in corso... ");
                char[] spinner = {'|', '/', '—', '\\'};
                int i = 0;
                while (!futureResult.isDone()) {
                    System.out.print("\b" + spinner[i++ % spinner.length]);
                    Thread.sleep(100);
                }
                lastCommandOutput = futureResult.get();
            } catch (Exception e) {
                lastCommandOutput = "Errore durante l'esecuzione del comando: " + e.getMessage();
            }
        }
    }

    /**
     * Termina l'applicazione, arrestando il thread pool per l'esecuzione dei comandi.
     */
    public void shutdown() {
        this.running = false;
        System.out.println("\nChiusura dei servizi in background...");
        commandExecutor.shutdown();
        try {
            if (!commandExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                commandExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            commandExecutor.shutdownNow();
        }
        System.out.println("Servizi terminati.");
    }

    /**
     * Pulisce la console e visualizza la dashboard principale con lo stato corrente
     * e l'output dell'ultimo comando.
     */
    private void displayDashboard() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        String header = "\n\n============================== MOVIE LIBRARY ==============================";
        String status = String.format(" Stato: %d film nella libreria.", library.getMovieCount());
        String commandsHeader = "---------------------------------------------------------------------------";
        String commandList = String.format(" %-25s %-25s %-25s\n", "cerca <titolo>", "aggiungi <numero>", "togli <numero>");
        String commandList2 = String.format(" %-25s %-25s %-25s", "vedi", "salva / carica", "esci / aiuto");
        String footer = "===========================================================================";
        String outputHeader = "--- Output ---";

        System.out.println(header);
        System.out.println(status);
        System.out.println(commandsHeader);
        System.out.println(commandList);
        System.out.println(commandList2);
        System.out.println(footer);
        System.out.println();
        System.out.println(outputHeader);
        System.out.println(lastCommandOutput);
        System.out.println();
    }

    /** Restituisce la libreria corrente. */
    public MovieCollection getLibrary() { return library; }
    /** Imposta la libreria corrente. */
    public void setLibrary(MovieCollection library) { this.library = library; }
    /** Restituisce gli ultimi risultati della ricerca. */
    public List<MovieDto> getLastSearchResults() { return lastSearchResults; }
    /** Imposta gli ultimi risultati della ricerca. */
    public void setLastSearchResults(List<MovieDto> results) { this.lastSearchResults = results; }
}
