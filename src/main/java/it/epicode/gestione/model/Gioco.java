package it.epicode.gestione.model;

import java.util.Objects;

/**
 * Classe astratta che rappresenta un gioco generico
 * Contiene gli attributi comuni a tutti i tipi di gioco
 */
public abstract class Gioco {
    protected String idGioco;
    protected String titolo;
    protected int annoPubblicazione;
    protected double prezzo;

    /**
     * Costruttore della classe Gioco
     * @param idGioco ID univoco del gioco
     * @param titolo Titolo del gioco
     * @param annoPubblicazione Anno di pubblicazione
     * @param prezzo Prezzo del gioco (deve essere positivo)
     * @throws IllegalArgumentException se i parametri non sono validi
     */
    public Gioco(String idGioco, String titolo, int annoPubblicazione, double prezzo) {
        if (idGioco == null || idGioco.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ID del gioco non può essere nullo o vuoto");
        }
        if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere nullo o vuoto");
        }
        if (annoPubblicazione < 1950 || annoPubblicazione > 2024) {
            throw new IllegalArgumentException("L'anno di pubblicazione deve essere compreso tra 1950 e 2024");
        }
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere un valore positivo");
        }

        this.idGioco = idGioco.trim();
        this.titolo = titolo.trim();
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    // Getters
    public String getIdGioco() {
        return idGioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    // Setters
    public void setTitolo(String titolo) {
        if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere nullo o vuoto");
        }
        this.titolo = titolo.trim();
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        if (annoPubblicazione < 1950 || annoPubblicazione > 2024) {
            throw new IllegalArgumentException("L'anno di pubblicazione deve essere compreso tra 1950 e 2024");
        }
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere un valore positivo");
        }
        this.prezzo = prezzo;
    }

    /**
     * Metodo astratto per ottenere il tipo di gioco
     * @return Il tipo di gioco come stringa
     */
    public abstract String getTipoGioco();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Gioco gioco = (Gioco) obj;
        return Objects.equals(idGioco, gioco.idGioco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGioco);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Titolo: %s, Anno: %d, Prezzo: €%.2f", 
                           idGioco, titolo, annoPubblicazione, prezzo);
    }
}