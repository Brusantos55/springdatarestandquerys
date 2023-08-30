package com.example.last.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RespStaException extends RuntimeException {
    public RespStaException() {
        super();
    }
    // public RespStaException(String message, Throwable cause) {
    //     super(message, cause);
    // }
    // public RespStaException(String message) {
    //     super(message);
    // }
    // public RespStaException(Throwable cause) {
    //     super(cause);
    // }
}