package it.epicode.gestione.model;

/**
 * Classe che rappresenta un videogioco
 * Estende la classe astratta Gioco aggiungendo attributi specifici
 */
public class Videogioco extends Gioco {
    private String piattaforma;
    private int durataGioco; // in ore
    private Genere genere;

    /**
     * Costruttore della classe Videogioco
     * @param idGioco ID univoco del gioco
     * @param titolo Titolo del gioco
     * @param annoPubblicazione Anno di pubblicazione
     * @param prezzo Prezzo del gioco
     * @param piattaforma Piattaforma di gioco (es. PC, PS5, XBOX)
     * @param durataGioco Durata del gioco in ore
     * @param genere Genere del videogioco
     */
    public Videogioco(String idGioco, String titolo, int annoPubblicazione, double prezzo,
                     String piattaforma, int durataGioco, Genere genere) {
        super(idGioco, titolo, annoPubblicazione, prezzo);
        
        if (piattaforma == null || piattaforma.trim().isEmpty()) {
            throw new IllegalArgumentException("La piattaforma non può essere nulla o vuota");
        }
        if (durataGioco <= 0) {
            throw new IllegalArgumentException("La durata del gioco deve essere un valore positivo");
        }
        if (genere == null) {
            throw new IllegalArgumentException("Il genere non può essere nullo");
        }

        this.piattaforma = piattaforma.trim();
        this.durataGioco = durataGioco;
        this.genere = genere;
    }

    // Getters
    public String getPiattaforma() {
        return piattaforma;
    }

    public int getDurataGioco() {
        return durataGioco;
    }

    public Genere getGenere() {
        return genere;
    }

    // Setters
    public void setPiattaforma(String piattaforma) {
        if (piattaforma == null || piattaforma.trim().isEmpty()) {
            throw new IllegalArgumentException("La piattaforma non può essere nulla o vuota");
        }
        this.piattaforma = piattaforma.trim();
    }

    public void setDurataGioco(int durataGioco) {
        if (durataGioco <= 0) {
            throw new IllegalArgumentException("La durata del gioco deve essere un valore positivo");
        }
        this.durataGioco = durataGioco;
    }

    public void setGenere(Genere genere) {
        if (genere == null) {
            throw new IllegalArgumentException("Il genere non può essere nullo");
        }
        this.genere = genere;
    }

    @Override
    public String getTipoGioco() {
        return "Videogioco";
    }

    @Override
    public String toString() {
        return String.format("%s, Tipo: %s, Piattaforma: %s, Durata: %d ore, Genere: %s",
                           super.toString(), getTipoGioco(), piattaforma, durataGioco, genere);
    }
}