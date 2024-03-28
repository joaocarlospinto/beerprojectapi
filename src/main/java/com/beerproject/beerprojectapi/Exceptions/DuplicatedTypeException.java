package com.beerproject.beerprojectapi.Exceptions;

public class DuplicatedTypeException extends RuntimeException{
    public DuplicatedTypeException() {
        super("Type already exists");
    }
    public DuplicatedTypeException(String message) {
        super(message);
    }

}
