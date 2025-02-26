package com.schneider.spring.springboot.autovermietungapp.exception;

/**
 * Exception thrown when no cars are found in the database.
 * <p>
 * This exception is used to handle situations where a request is made for cars, but there are no cars available in the database.
 */
public class CarsNotExistInDataBaseException extends RuntimeException {

    /**
     * Constructor for CarsNotExistInDataBaseException.
     *
     * @param message the detail message to be reported when the exception is thrown.
     */
    public CarsNotExistInDataBaseException(String message) {
        super(message);
    }
}