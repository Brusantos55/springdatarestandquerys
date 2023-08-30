package com.example.last.exceptions;


public class NameRepeatedException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Ya existe un jugador con el nombre ";
    
    public NameRepeatedException(String name) {
       super(DEFAULT_MESSAGE + name);
    }
}
