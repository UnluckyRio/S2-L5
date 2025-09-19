package it.epicode.gestione.service;

import it.epicode.gestione.exception.ElementoDuplicatoException;
import it.epicode.gestione.exception.ElementoNonTrovatoException;
import it.epicode.gestione.model.Gioco;
import it.epicode.gestione.model.GiocoDaTavolo;
import it.epicode.gestione.model.Videogioco;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe che gestisce una collezione di giochi
 * Implementa tutti i metodi richiesti utilizzando Java Streams e Lambda functions
 */
public class Collezione {
    private final Map<String, Gioco> giochi;

    /**
     * Costruttore della classe Collezione
     */
    public Collezione() {
        this.giochi = new HashMap<>();
    }

    /**
     * Aggiunge un elemento alla collezione
     * Blocca l'inserimento se l'ID esiste già
     * @param gioco Il gioco da aggiungere
     * @throws ElementoDuplicatoException se l'ID esiste già
     */
    public void aggiungiElemento(Gioco gioco) throws ElementoDuplicatoException {
        if (gioco == null) {
            throw new IllegalArgumentException("Il gioco non può essere nullo");
        }
        
        if (giochi.containsKey(gioco.getIdGioco())) {
            throw new ElementoDuplicatoException(
                "Elemento con ID '" + gioco.getIdGioco() + "' già presente nella collezione");
        }
        
        giochi.put(gioco.getIdGioco(), gioco);
    }

    /**
     * Ricerca un elemento tramite ID
     * @param id L'ID del gioco da cercare
     * @return Il gioco trovato
     * @throws ElementoNonTrovatoException se l'elemento non viene trovato
     */
    public Gioco cercaPerID(String id) throws ElementoNonTrovatoException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ID non può essere nullo o vuoto");
        }
        
        return Optional.ofNullable(giochi.get(id.trim()))
                .orElseThrow(() -> new ElementoNonTrovatoException(
                    "Elemento con ID '" + id + "' non trovato nella collezione"));
    }

    /**
     * Restituisce una lista di giochi con prezzo inferiore al valore specificato
     * @param prezzoMassimo Il prezzo massimo
     * @return Lista di giochi con prezzo inferiore al valore specificato
     */
    public List<Gioco> cercaPerPrezzo(double prezzoMassimo) {
        if (prezzoMassimo <= 0) {
            throw new IllegalArgumentException("Il prezzo massimo deve essere un valore positivo");
        }
        
        return giochi.values().stream()
                .filter(gioco -> gioco.getPrezzo() < prezzoMassimo)
                .sorted(Comparator.comparing(Gioco::getPrezzo))
                .collect(Collectors.toList());
    }

    /**
     * Ricerca giochi da tavolo per numero di giocatori
     * @param numeroGiocatori Il numero di giocatori
     * @return Lista di giochi da tavolo per il numero di giocatori specificato
     */
    public List<GiocoDaTavolo> cercaPerNumeroGiocatori(int numeroGiocatori) {
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 10");
        }
        
        return giochi.values().stream()
                .filter(gioco -> gioco instanceof GiocoDaTavolo)
                .map(gioco -> (GiocoDaTavolo) gioco)
                .filter(giocoDaTavolo -> giocoDaTavolo.getNumeroGiocatori() == numeroGiocatori)
                .sorted(Comparator.comparing(Gioco::getTitolo))
                .collect(Collectors.toList());
    }

    /**
     * Elimina un elemento dato il suo ID
     * @param id L'ID del gioco da eliminare
     * @throws ElementoNonTrovatoException se l'elemento non viene trovato
     */
    public void rimuoviElemento(String id) throws ElementoNonTrovatoException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ID non può essere nullo o vuoto");
        }
        
        if (!giochi.containsKey(id.trim())) {
            throw new ElementoNonTrovatoException(
                "Elemento con ID '" + id + "' non trovato nella collezione");
        }
        
        giochi.remove(id.trim());
    }

    /**
     * Modifica un elemento esistente identificato tramite ID
     * @param giocoAggiornato Il gioco con i dati aggiornati
     * @throws ElementoNonTrovatoException se l'elemento non viene trovato
     */
    public void aggiornaElemento(Gioco giocoAggiornato) throws ElementoNonTrovatoException {
        if (giocoAggiornato == null) {
            throw new IllegalArgumentException("Il gioco aggiornato non può essere nullo");
        }
        
        if (!giochi.containsKey(giocoAggiornato.getIdGioco())) {
            throw new ElementoNonTrovatoException(
                "Elemento con ID '" + giocoAggiornato.getIdGioco() + "' non trovato nella collezione");
        }
        
        giochi.put(giocoAggiornato.getIdGioco(), giocoAggiornato);
    }

    /**
     * Genera statistiche sulla collezione
     * @return Mappa con le statistiche
     */
    public Map<String, Object> generaStatistiche() {
        Map<String, Object> statistiche = new HashMap<>();
        
        if (giochi.isEmpty()) {
            statistiche.put("totaleVideogiochi", 0);
            statistiche.put("totaleGiochiDaTavolo", 0);
            statistiche.put("giocoPrezzoPiuElevato", null);
            statistiche.put("mediaPrezzi", 0.0);
            return statistiche;
        }
        
        // Conteggio videogiochi
        long totaleVideogiochi = giochi.values().stream()
                .filter(gioco -> gioco instanceof Videogioco)
                .count();
        
        // Conteggio giochi da tavolo
        long totaleGiochiDaTavolo = giochi.values().stream()
                .filter(gioco -> gioco instanceof GiocoDaTavolo)
                .count();
        
        // Gioco con prezzo più elevato
        Optional<Gioco> giocoPrezzoPiuElevato = giochi.values().stream()
                .max(Comparator.comparing(Gioco::getPrezzo));
        
        // Media dei prezzi
        double mediaPrezzi = giochi.values().stream()
                .mapToDouble(Gioco::getPrezzo)
                .average()
                .orElse(0.0);
        
        statistiche.put("totaleVideogiochi", totaleVideogiochi);
        statistiche.put("totaleGiochiDaTavolo", totaleGiochiDaTavolo);
        statistiche.put("giocoPrezzoPiuElevato", giocoPrezzoPiuElevato.orElse(null));
        statistiche.put("mediaPrezzi", mediaPrezzi);
        
        return statistiche;
    }

    /**
     * Restituisce tutti i giochi nella collezione
     * @return Lista di tutti i giochi
     */
    public List<Gioco> getTuttiIGiochi() {
        return new ArrayList<>(giochi.values());
    }

    /**
     * Restituisce il numero totale di giochi nella collezione
     * @return Il numero totale di giochi
     */
    public int size() {
        return giochi.size();
    }

    /**
     * Verifica se la collezione è vuota
     * @return true se la collezione è vuota, false altrimenti
     */
    public boolean isEmpty() {
        return giochi.isEmpty();
    }
}