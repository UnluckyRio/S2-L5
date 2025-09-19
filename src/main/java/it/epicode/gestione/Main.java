package it.epicode.gestione;

import it.epicode.gestione.ui.MenuPrincipale;

/**
 * Classe principale dell'applicazione per la gestione della collezione di giochi
 * 
 * @author Epicode
 * @version 1.0.0
 */
public class Main {
    
    /**
     * Metodo main per avviare l'applicazione
     * @param args Argomenti della riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        try {
            // Crea e avvia il menu principale
            MenuPrincipale menu = new MenuPrincipale();
            menu.avvia();
        } catch (Exception e) {
            System.err.println("Errore critico nell'avvio dell'applicazione: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}