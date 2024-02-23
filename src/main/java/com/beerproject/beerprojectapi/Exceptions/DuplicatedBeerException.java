package com.beerproject.beerprojectapi.Exceptions;

public class DuplicatedBeerException extends RuntimeException {
    public DuplicatedBeerException() {
        super("Beer already Exists");
    }

    public DuplicatedBeerException(String message) {
        super(message);
    }
}
