package com.schneider.spring.springboot.autovermietungapp.controller.handler;

import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.IncorrectBrandNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarsNotExistInDataBaseException.class)
    public ResponseEntity<String> handleCarsNotExistException(CarsNotExistInDataBaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectBrandNameException.class)
    public ResponseEntity<String> handleIncorrectBrandNameException(IncorrectBrandNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}