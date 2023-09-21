package com.example.last.exception;

public class NameRepeatedException extends RuntimeException{
	private static final long serialVersionUID = 2L;
	private static final String DEFAULT_MESSAGE = "Ya existe un Empleado con el nombre ";
    
    public NameRepeatedException(String name) {
       super(DEFAULT_MESSAGE + name);
    }
}
