package com.schneider.spring.springboot.autovermietungapp.exception;

public class UsersNotExistInDataBaseException extends RuntimeException {
    public UsersNotExistInDataBaseException(String message) {
        super(message);
    }
}