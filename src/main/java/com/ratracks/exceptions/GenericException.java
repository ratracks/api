package com.ratracks.exceptions;

public abstract class GenericException extends RuntimeException {
    
    protected GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
