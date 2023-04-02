package com.ratracks.exceptions;

public abstract class GenericException extends RuntimeException {
    
    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
