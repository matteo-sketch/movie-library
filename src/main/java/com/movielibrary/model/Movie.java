package com.movielibrary.model;

/**
 * Rappresenta un singolo film nella libreria.
 * Questa classe è una "foglia" nel pattern Composite, implementando {@link LibraryComponent}.
 */
public class Movie implements LibraryComponent {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String title;
    private final String overview;
    private final String releaseDate;

    /**
     * Costruisce un nuovo oggetto Movie.
     *
     * @param id L'identificativo univoco del film.
     * @param title Il titolo del film.
     * @param overview Una breve trama del film.
     * @param releaseDate La data di uscita del film.
     */
    public Movie(int id, String title, String overview, String releaseDate) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    /**
     * Restituisce il titolo del film.
     *
     * @return Il titolo del film.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Restituisce una rappresentazione testuale del film.
     *
     * @param indent L'indentazione da applicare (non usata per un singolo film).
     * @return Una stringa con i dettagli del film.
     */
    @Override
    public String display(String indent) {
        String date = (releaseDate == null || releaseDate.isEmpty()) ? "N/A" : releaseDate;
        return String.format("%s- Film: %s (Uscita: %s)", indent, title, date);
    }

    /**
     * Restituisce il conteggio dei film, che per questa classe è sempre 1.
     *
     * @return Il numero 1.
     */
    @Override
    public int getMovieCount() {
        return 1;
    }
}
