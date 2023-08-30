package com.example.last.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLSyntaxErrorException;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(NameRepeatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleNameException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class) 
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class) 
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleSQLException(Exception ex){
        return new ResponseEntity<>("\n sql error, last time ; wrong placed fixed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @ExceptionHandler(RespStaException.class) 
    // @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    // public ResponseEntity<Object> handleCustomException(Exception ex){
    //     return new ResponseEntity<>("Custom Error message", HttpStatus.I_AM_A_TEAPOT);
    // }
    
} // https://docs.spring.io/spring-boot/docs/2.7.15/reference/htmlsingle/#web.servlet.spring-mvc.error-handling