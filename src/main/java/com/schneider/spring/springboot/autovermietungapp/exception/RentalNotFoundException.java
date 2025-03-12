package com.schneider.spring.springboot.autovermietungapp.exception;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(int id) {
        super("Rental with ID " + id + " not found");
    }
}

