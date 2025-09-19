package it.epicode.gestione.model;

/**
 * Enum che rappresenta i generi disponibili per i videogiochi
 */
public enum Genere {
    AZIONE("Azione"),
    AVVENTURA("Avventura"),
    RPG("RPG"),
    STRATEGIA("Strategia"),
    SIMULAZIONE("Simulazione"),
    SPORT("Sport"),
    CORSA("Corsa"),
    PUZZLE("Puzzle"),
    HORROR("Horror"),
    SPARATUTTO("Sparatutto"),
    PLATFORM("Platform"),
    ARCADE("Arcade");

    private final String descrizione;

    /**
     * Costruttore dell'enum
     * @param descrizione La descrizione del genere
     */
    Genere(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce la descrizione del genere
     * @return La descrizione del genere
     */
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return descrizione;
    }
}