package com.schneider.spring.springboot.autovermietungapp.controller.handler;

import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.IncorrectBrandNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to manage different types of exceptions across the application.
 * <p>
 * This class handles the exceptions related to cars not existing in the database and
 * incorrect brand names and returns a meaningful error message with appropriate HTTP status codes.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the CarsNotExistInDataBaseException and sends a 400 BAD REQUEST response.
     *
     * @param ex the exception instance
     * @return ResponseEntity with error message and status code
     */
    @ExceptionHandler(CarsNotExistInDataBaseException.class)
    public ResponseEntity<String> handleCarsNotExistException(CarsNotExistInDataBaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles the IncorrectBrandNameException and sends a 400 BAD REQUEST response.
     *
     * @param ex the exception instance
     * @return ResponseEntity with error message and status code
     */
    @ExceptionHandler(IncorrectBrandNameException.class)
    public ResponseEntity<String> handleIncorrectBrandNameException(IncorrectBrandNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}