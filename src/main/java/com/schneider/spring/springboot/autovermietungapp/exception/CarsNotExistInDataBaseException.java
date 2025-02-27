package com.schneider.spring.springboot.autovermietungapp.exception;

/**
 * Exception thrown when no cars are found in the database.
 * <p>
 * This exception is used to handle situations where a request is made for cars, but there are no cars available in the database.
 */

public class CarsNotExistInDataBaseException extends RuntimeException {
    public CarsNotExistInDataBaseException(String message) {
        super(message);
    }
}