package it.epicode.gestione.ui;

import it.epicode.gestione.exception.ElementoDuplicatoException;
import it.epicode.gestione.exception.ElementoNonTrovatoException;
import it.epicode.gestione.exception.InputNonValidoException;
import it.epicode.gestione.model.*;
import it.epicode.gestione.service.Collezione;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe che gestisce l'interfaccia utente dell'applicazione
 * Fornisce un menu interattivo per accedere a tutte le operazioni
 */
public class MenuPrincipale {
    private final Scanner scanner;
    private final Collezione collezione;

    /**
     * Costruttore della classe MenuPrincipale
     */
    public MenuPrincipale() {
        this.scanner = new Scanner(System.in);
        this.collezione = new Collezione();
    }

    /**
     * Avvia l'applicazione e mostra il menu principale
     */
    public void avvia() {
        System.out.println("=== GESTIONE COLLEZIONE GIOCHI ===");
        System.out.println("Benvenuto nell'applicazione per la gestione della tua collezione di giochi!");
        
        boolean continua = true;
        while (continua) {
            try {
                mostraMenu();
                int scelta = leggiScelta();
                continua = elaboraScelta(scelta);
            } catch (Exception e) {
                System.err.println("Errore imprevisto: " + e.getMessage());
                System.out.println("Premi INVIO per continuare...");
                scanner.nextLine();
            }
        }
        
        System.out.println("Grazie per aver utilizzato l'applicazione!");
        scanner.close();
    }

    /**
     * Mostra il menu principale
     */
    private void mostraMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MENU PRINCIPALE");
        System.out.println("=".repeat(50));
        System.out.println("1. Aggiungi gioco");
        System.out.println("2. Cerca gioco per ID");
        System.out.println("3. Cerca giochi per prezzo massimo");
        System.out.println("4. Cerca giochi da tavolo per numero giocatori");
        System.out.println("5. Rimuovi gioco");
        System.out.println("6. Aggiorna gioco");
        System.out.println("7. Visualizza statistiche");
        System.out.println("8. Visualizza tutti i giochi");
        System.out.println("0. Esci");
        System.out.println("=".repeat(50));
        System.out.print("Inserisci la tua scelta: ");
    }

    /**
     * Legge la scelta dell'utente
     * @return La scelta dell'utente
     */
    private int leggiScelta() {
        try {
            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline
            return scelta;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Pulisce l'input non valido
            System.err.println("Errore: Inserisci un numero valido!");
            return -1;
        }
    }

    /**
     * Elabora la scelta dell'utente
     * @param scelta La scelta dell'utente
     * @return true per continuare, false per uscire
     */
    private boolean elaboraScelta(int scelta) {
        try {
            switch (scelta) {
                case 1:
                    aggiungiGioco();
                    break;
                case 2:
                    cercaGiocoPerID();
                    break;
                case 3:
                    cercaGiochiPerPrezzo();
                    break;
                case 4:
                    cercaGiochiPerNumeroGiocatori();
                    break;
                case 5:
                    rimuoviGioco();
                    break;
                case 6:
                    aggiornaGioco();
                    break;
                case 7:
                    visualizzaStatistiche();
                    break;
                case 8:
                    visualizzaTuttiIGiochi();
                    break;
                case 0:
                    return false;
                default:
                    System.err.println("Scelta non valida! Riprova.");
            }
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
        
        System.out.println("\nPremi INVIO per continuare...");
        scanner.nextLine();
        return true;
    }

    /**
     * Gestisce l'aggiunta di un nuovo gioco
     */
    private void aggiungiGioco() throws InputNonValidoException, ElementoDuplicatoException {
        System.out.println("\n--- AGGIUNGI GIOCO ---");
        System.out.println("Che tipo di gioco vuoi aggiungere?");
        System.out.println("1. Videogioco");
        System.out.println("2. Gioco da Tavolo");
        System.out.print("Scelta: ");
        
        int tipo = leggiIntero();
        
        switch (tipo) {
            case 1:
                aggiungiVideogioco();
                break;
            case 2:
                aggiungiGiocoDaTavolo();
                break;
            default:
                throw new InputNonValidoException("Tipo di gioco non valido!");
        }
    }

    /**
     * Aggiunge un videogioco alla collezione
     */
    private void aggiungiVideogioco() throws InputNonValidoException, ElementoDuplicatoException {
        System.out.println("\n--- AGGIUNGI VIDEOGIOCO ---");
        
        String id = leggiStringa("ID Gioco: ");
        String titolo = leggiStringa("Titolo: ");
        int anno = leggiIntero("Anno di pubblicazione: ");
        double prezzo = leggiDouble("Prezzo (€): ");
        String piattaforma = leggiStringa("Piattaforma: ");
        int durata = leggiIntero("Durata di gioco (ore): ");
        
        System.out.println("\nGeneri disponibili:");
        Genere[] generi = Genere.values();
        for (int i = 0; i < generi.length; i++) {
            System.out.println((i + 1) + ". " + generi[i]);
        }
        System.out.print("Scegli il genere (1-" + generi.length + "): ");
        int sceltaGenere = leggiIntero() - 1;
        
        if (sceltaGenere < 0 || sceltaGenere >= generi.length) {
            throw new InputNonValidoException("Genere non valido!");
        }
        
        Videogioco videogioco = new Videogioco(id, titolo, anno, prezzo, piattaforma, durata, generi[sceltaGenere]);
        collezione.aggiungiElemento(videogioco);
        
        System.out.println("✓ Videogioco aggiunto con successo!");
    }

    /**
     * Aggiunge un gioco da tavolo alla collezione
     */
    private void aggiungiGiocoDaTavolo() throws InputNonValidoException, ElementoDuplicatoException {
        System.out.println("\n--- AGGIUNGI GIOCO DA TAVOLO ---");
        
        String id = leggiStringa("ID Gioco: ");
        String titolo = leggiStringa("Titolo: ");
        int anno = leggiIntero("Anno di pubblicazione: ");
        double prezzo = leggiDouble("Prezzo (€): ");
        int numeroGiocatori = leggiIntero("Numero di giocatori (2-10): ");
        int durataPartita = leggiIntero("Durata media partita (minuti): ");
        
        GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(id, titolo, anno, prezzo, numeroGiocatori, durataPartita);
        collezione.aggiungiElemento(giocoDaTavolo);
        
        System.out.println("✓ Gioco da tavolo aggiunto con successo!");
    }

    /**
     * Cerca un gioco per ID
     */
    private void cercaGiocoPerID() throws ElementoNonTrovatoException {
        System.out.println("\n--- CERCA GIOCO PER ID ---");
        String id = leggiStringa("Inserisci l'ID del gioco: ");
        
        Gioco gioco = collezione.cercaPerID(id);
        System.out.println("\n✓ Gioco trovato:");
        System.out.println(gioco);
    }

    /**
     * Cerca giochi per prezzo massimo
     */
    private void cercaGiochiPerPrezzo() {
        System.out.println("\n--- CERCA GIOCHI PER PREZZO ---");
        double prezzoMax = leggiDouble("Inserisci il prezzo massimo (€): ");
        
        List<Gioco> giochi = collezione.cercaPerPrezzo(prezzoMax);
        
        if (giochi.isEmpty()) {
            System.out.println("Nessun gioco trovato con prezzo inferiore a €" + prezzoMax);
        } else {
            System.out.println("\n✓ Giochi trovati (" + giochi.size() + "):");
            giochi.forEach(System.out::println);
        }
    }

    /**
     * Cerca giochi da tavolo per numero di giocatori
     */
    private void cercaGiochiPerNumeroGiocatori() {
        System.out.println("\n--- CERCA GIOCHI DA TAVOLO PER NUMERO GIOCATORI ---");
        int numeroGiocatori = leggiIntero("Inserisci il numero di giocatori (2-10): ");
        
        List<GiocoDaTavolo> giochi = collezione.cercaPerNumeroGiocatori(numeroGiocatori);
        
        if (giochi.isEmpty()) {
            System.out.println("Nessun gioco da tavolo trovato per " + numeroGiocatori + " giocatori");
        } else {
            System.out.println("\n✓ Giochi da tavolo trovati (" + giochi.size() + "):");
            giochi.forEach(System.out::println);
        }
    }

    /**
     * Rimuove un gioco dalla collezione
     */
    private void rimuoviGioco() throws ElementoNonTrovatoException {
        System.out.println("\n--- RIMUOVI GIOCO ---");
        String id = leggiStringa("Inserisci l'ID del gioco da rimuovere: ");
        
        // Prima mostra il gioco che verrà rimosso
        Gioco gioco = collezione.cercaPerID(id);
        System.out.println("\nGioco da rimuovere:");
        System.out.println(gioco);
        
        System.out.print("Sei sicuro di voler rimuovere questo gioco? (s/N): ");
        String conferma = scanner.nextLine().trim().toLowerCase();
        
        if (conferma.equals("s") || conferma.equals("si")) {
            collezione.rimuoviElemento(id);
            System.out.println("✓ Gioco rimosso con successo!");
        } else {
            System.out.println("Operazione annullata.");
        }
    }

    /**
     * Aggiorna un gioco esistente
     */
    private void aggiornaGioco() throws ElementoNonTrovatoException, InputNonValidoException {
        System.out.println("\n--- AGGIORNA GIOCO ---");
        String id = leggiStringa("Inserisci l'ID del gioco da aggiornare: ");
        
        // Verifica che il gioco esista
        Gioco giocoEsistente = collezione.cercaPerID(id);
        System.out.println("\nGioco attuale:");
        System.out.println(giocoEsistente);
        
        System.out.println("\nInserisci i nuovi dati (premi INVIO per mantenere il valore attuale):");
        
        if (giocoEsistente instanceof Videogioco) {
            aggiornaVideogioco((Videogioco) giocoEsistente);
        } else if (giocoEsistente instanceof GiocoDaTavolo) {
            aggiornaGiocoDaTavolo((GiocoDaTavolo) giocoEsistente);
        }
        
        System.out.println("✓ Gioco aggiornato con successo!");
    }

    /**
     * Aggiorna un videogioco esistente
     */
    private void aggiornaVideogioco(Videogioco videogioco) throws ElementoNonTrovatoException, InputNonValidoException {
        String nuovoTitolo = leggiStringaOpzionale("Titolo [" + videogioco.getTitolo() + "]: ");
        if (!nuovoTitolo.isEmpty()) {
            videogioco.setTitolo(nuovoTitolo);
        }
        
        String nuovoAnno = leggiStringaOpzionale("Anno [" + videogioco.getAnnoPubblicazione() + "]: ");
        if (!nuovoAnno.isEmpty()) {
            videogioco.setAnnoPubblicazione(Integer.parseInt(nuovoAnno));
        }
        
        String nuovoPrezzo = leggiStringaOpzionale("Prezzo [€" + videogioco.getPrezzo() + "]: ");
        if (!nuovoPrezzo.isEmpty()) {
            videogioco.setPrezzo(Double.parseDouble(nuovoPrezzo));
        }
        
        String nuovaPiattaforma = leggiStringaOpzionale("Piattaforma [" + videogioco.getPiattaforma() + "]: ");
        if (!nuovaPiattaforma.isEmpty()) {
            videogioco.setPiattaforma(nuovaPiattaforma);
        }
        
        String nuovaDurata = leggiStringaOpzionale("Durata [" + videogioco.getDurataGioco() + " ore]: ");
        if (!nuovaDurata.isEmpty()) {
            videogioco.setDurataGioco(Integer.parseInt(nuovaDurata));
        }
        
        collezione.aggiornaElemento(videogioco);
    }

    /**
     * Aggiorna un gioco da tavolo esistente
     */
    private void aggiornaGiocoDaTavolo(GiocoDaTavolo giocoDaTavolo) throws ElementoNonTrovatoException, InputNonValidoException {
        String nuovoTitolo = leggiStringaOpzionale("Titolo [" + giocoDaTavolo.getTitolo() + "]: ");
        if (!nuovoTitolo.isEmpty()) {
            giocoDaTavolo.setTitolo(nuovoTitolo);
        }
        
        String nuovoAnno = leggiStringaOpzionale("Anno [" + giocoDaTavolo.getAnnoPubblicazione() + "]: ");
        if (!nuovoAnno.isEmpty()) {
            giocoDaTavolo.setAnnoPubblicazione(Integer.parseInt(nuovoAnno));
        }
        
        String nuovoPrezzo = leggiStringaOpzionale("Prezzo [€" + giocoDaTavolo.getPrezzo() + "]: ");
        if (!nuovoPrezzo.isEmpty()) {
            giocoDaTavolo.setPrezzo(Double.parseDouble(nuovoPrezzo));
        }
        
        String nuovoNumeroGiocatori = leggiStringaOpzionale("Numero giocatori [" + giocoDaTavolo.getNumeroGiocatori() + "]: ");
        if (!nuovoNumeroGiocatori.isEmpty()) {
            giocoDaTavolo.setNumeroGiocatori(Integer.parseInt(nuovoNumeroGiocatori));
        }
        
        String nuovaDurataPartita = leggiStringaOpzionale("Durata partita [" + giocoDaTavolo.getDurataMediaPartita() + " min]: ");
        if (!nuovaDurataPartita.isEmpty()) {
            giocoDaTavolo.setDurataMediaPartita(Integer.parseInt(nuovaDurataPartita));
        }
        
        collezione.aggiornaElemento(giocoDaTavolo);
    }

    /**
     * Visualizza le statistiche della collezione
     */
    private void visualizzaStatistiche() {
        System.out.println("\n--- STATISTICHE COLLEZIONE ---");
        
        if (collezione.isEmpty()) {
            System.out.println("La collezione è vuota. Nessuna statistica disponibile.");
            return;
        }
        
        Map<String, Object> statistiche = collezione.generaStatistiche();
        
        System.out.println("Totale videogiochi: " + statistiche.get("totaleVideogiochi"));
        System.out.println("Totale giochi da tavolo: " + statistiche.get("totaleGiochiDaTavolo"));
        System.out.println("Totale giochi: " + collezione.size());
        
        Gioco giocoPrezzoPiuElevato = (Gioco) statistiche.get("giocoPrezzoPiuElevato");
        if (giocoPrezzoPiuElevato != null) {
            System.out.println("\nGioco con prezzo più elevato:");
            System.out.println(giocoPrezzoPiuElevato);
        }
        
        double mediaPrezzi = (Double) statistiche.get("mediaPrezzi");
        System.out.printf("\nMedia prezzi: €%.2f%n", mediaPrezzi);
    }

    /**
     * Visualizza tutti i giochi nella collezione
     */
    private void visualizzaTuttiIGiochi() {
        System.out.println("\n--- TUTTI I GIOCHI ---");
        
        if (collezione.isEmpty()) {
            System.out.println("La collezione è vuota.");
            return;
        }
        
        List<Gioco> giochi = collezione.getTuttiIGiochi();
        System.out.println("Totale giochi: " + giochi.size());
        System.out.println();
        
        giochi.stream()
                .sorted((g1, g2) -> g1.getTitolo().compareToIgnoreCase(g2.getTitolo()))
                .forEach(System.out::println);
    }

    // Metodi di utilità per la lettura dell'input

    private String leggiStringa(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String leggiStringaOpzionale(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int leggiIntero() {
        while (true) {
            try {
                int valore = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline
                return valore;
            } catch (InputMismatchException e) {
                System.err.print("Errore: Inserisci un numero intero valido: ");
                scanner.nextLine(); // Pulisce l'input non valido
            }
        }
    }

    private int leggiIntero(String prompt) {
        System.out.print(prompt);
        return leggiIntero();
    }

    private double leggiDouble(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                double valore = scanner.nextDouble();
                scanner.nextLine(); // Consuma il newline
                return valore;
            } catch (InputMismatchException e) {
                System.err.print("Errore: Inserisci un numero decimale valido: ");
                scanner.nextLine(); // Pulisce l'input non valido
            }
        }
    }
}