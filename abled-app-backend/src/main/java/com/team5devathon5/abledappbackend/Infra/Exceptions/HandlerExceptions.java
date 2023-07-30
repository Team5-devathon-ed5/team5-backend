package com.team5devathon5.abledappbackend.Infra.Exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Objects;

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        String errorMessage = ex.getMostSpecificCause().getMessage();
        if (errorMessage.contains("users.unique_username")){
            return new ResponseEntity<>("This Username is already use.", HttpStatus.BAD_REQUEST);
        } else if (errorMessage.contains("users.email")) {
            return new ResponseEntity<>("This Email is already use", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Error processing the information check that the fields are not empty", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> fieldBlankException (MethodArgumentNotValidException ex){
        String fieldError = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField();
        String messageError = "This field: "+ fieldError + ", cannot be empty.";
        return new ResponseEntity<>(messageError,HttpStatus.BAD_REQUEST);
    }
}
