package com.beerproject.beerprojectapi.infra;

import com.beerproject.beerprojectapi.Exceptions.BeerNotFoundException;
import com.beerproject.beerprojectapi.Exceptions.DuplicatedBeerException;
import com.beerproject.beerprojectapi.Exceptions.DuplicatedTypeException;
import com.beerproject.beerprojectapi.Exceptions.TypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BeerNotFoundException.class)
    private ResponseEntity<String> beerNotFoundHandler(BeerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beer not found");
    }

    @ExceptionHandler(TypeNotFoundException.class)
    private ResponseEntity<String> typeNotFoundHandler(TypeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
    }

    @ExceptionHandler(DuplicatedBeerException.class)
    private ResponseEntity<String> duplicatedBeerHandler(DuplicatedBeerException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Beer already exists");
    }

    @ExceptionHandler(DuplicatedTypeException.class)
    private ResponseEntity<String> duplicatedTypeHandler(DuplicatedTypeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Type already exists");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handle (RuntimeException ex) {
        ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
