package it.epicode.gestione.exception;

/**
 * Eccezione lanciata quando si tenta di aggiungere un elemento con ID già esistente
 */
public class ElementoDuplicatoException extends Exception {
    
    public ElementoDuplicatoException(String message) {
        super(message);
    }
    
    public ElementoDuplicatoException(String message, Throwable cause) {
        super(message, cause);
    }
}