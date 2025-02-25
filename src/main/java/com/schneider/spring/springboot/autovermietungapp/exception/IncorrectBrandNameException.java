package com.schneider.spring.springboot.autovermietungapp.exception;

public class IncorrectBrandNameException extends RuntimeException {
    public IncorrectBrandNameException(String message) {
        super(message);
    }
}