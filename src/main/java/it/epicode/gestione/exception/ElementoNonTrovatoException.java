package it.epicode.gestione.exception;

/**
 * Eccezione lanciata quando un elemento non viene trovato nella collezione
 */
public class ElementoNonTrovatoException extends Exception {
    
    public ElementoNonTrovatoException(String message) {
        super(message);
    }
    
    public ElementoNonTrovatoException(String message, Throwable cause) {
        super(message, cause);
    }
}