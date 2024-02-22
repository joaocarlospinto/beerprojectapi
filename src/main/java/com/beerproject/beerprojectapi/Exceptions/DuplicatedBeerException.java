package com.beerproject.beerprojectapi.Exceptions;

public class DuplicatedBeerException extends RuntimeException {
    public DuplicatedBeerException() {
        super("Beer not found");
    }

    public DuplicatedBeerException(String message) {
        super(message);
    }
}
