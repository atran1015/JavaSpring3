package com.springboot.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@ControllerAdvice // Allows handling exceptions across the whole application in one global handling component
public class GlobalExceptionHandler {

    // Handles wrong input type (ex: putting string in an int field)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMessageNotReadableException() {
        return new ResponseEntity<>("Wrong input type", HttpStatus.BAD_REQUEST);
    }

    // Handles validation failure (ex: null input on required fields)
    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<String> handleMethodArgumentNotValidExpcetion() {
        return new ResponseEntity<>("Validation failed", HttpStatus.BAD_REQUEST);
    }

    // Handles wrong path (ex: using post on a url that doesn't support it http://localhost:8080/api/v1/gliders/{id}) 
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class) 
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException() {
        return new ResponseEntity<>("This method does not support this request", HttpStatus.METHOD_NOT_ALLOWED);
    }
    
}