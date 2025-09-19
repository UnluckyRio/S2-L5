package it.epicode.gestione.exception;

/**
 * Eccezione lanciata quando l'input fornito dall'utente non è valido
 */
public class InputNonValidoException extends Exception {
    
    public InputNonValidoException(String message) {
        super(message);
    }
    
    public InputNonValidoException(String message, Throwable cause) {
        super(message, cause);
    }
}