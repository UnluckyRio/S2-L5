package it.epicode.gestione.model;

/**
 * Classe che rappresenta un gioco da tavolo
 * Estende la classe astratta Gioco aggiungendo attributi specifici
 */
public class GiocoDaTavolo extends Gioco {
    private int numeroGiocatori; // range da 2 a 10
    private int durataMediaPartita; // in minuti

    /**
     * Costruttore della classe GiocoDaTavolo
     * @param idGioco ID univoco del gioco
     * @param titolo Titolo del gioco
     * @param annoPubblicazione Anno di pubblicazione
     * @param prezzo Prezzo del gioco
     * @param numeroGiocatori Numero di giocatori (da 2 a 10)
     * @param durataMediaPartita Durata media di una partita in minuti
     */
    public GiocoDaTavolo(String idGioco, String titolo, int annoPubblicazione, double prezzo,
                        int numeroGiocatori, int durataMediaPartita) {
        super(idGioco, titolo, annoPubblicazione, prezzo);
        
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 10");
        }
        if (durataMediaPartita <= 0) {
            throw new IllegalArgumentException("La durata media della partita deve essere un valore positivo");
        }

        this.numeroGiocatori = numeroGiocatori;
        this.durataMediaPartita = durataMediaPartita;
    }

    // Getters
    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public int getDurataMediaPartita() {
        return durataMediaPartita;
    }

    // Setters
    public void setNumeroGiocatori(int numeroGiocatori) {
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 10");
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public void setDurataMediaPartita(int durataMediaPartita) {
        if (durataMediaPartita <= 0) {
            throw new IllegalArgumentException("La durata media della partita deve essere un valore positivo");
        }
        this.durataMediaPartita = durataMediaPartita;
    }

    @Override
    public String getTipoGioco() {
        return "Gioco da Tavolo";
    }

    @Override
    public String toString() {
        return String.format("%s, Tipo: %s, Giocatori: %d, Durata partita: %d minuti",
                           super.toString(), getTipoGioco(), numeroGiocatori, durataMediaPartita);
    }
}