package com.schneider.spring.springboot.autovermietungapp.exception;

public class CarsNotExistInDataBaseException extends RuntimeException {

    public CarsNotExistInDataBaseException(String message) {
        super(message);
    }
}