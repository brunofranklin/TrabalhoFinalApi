package org.serratec.exception;

public class CategoriaException extends RuntimeException {
    
    public CategoriaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoriaException(String message) {
        super(message);
    }

}
