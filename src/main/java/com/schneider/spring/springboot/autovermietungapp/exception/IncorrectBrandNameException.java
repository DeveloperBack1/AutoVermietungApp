package com.schneider.spring.springboot.autovermietungapp.exception;

/**
 * Exception thrown when an incorrect car brand name is provided.
 * <p>
 * This exception is used to handle situations where an invalid brand name is supplied
 * and does not match any available brands in the system.
 */
public class IncorrectBrandNameException extends RuntimeException {

    /**
     * Constructor for IncorrectBrandNameException.
     *
     * @param message the detail message to be reported when the exception is thrown.
     */
    public IncorrectBrandNameException(String message) {
        super(message);
    }
}
