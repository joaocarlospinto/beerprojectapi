package com.beerproject.beerprojectapi.Exceptions;

public class BeerNotFoundException extends RuntimeException{
    public BeerNotFoundException() {super("Beer not found");}

    public BeerNotFoundException(String message) {super(message);}
}
