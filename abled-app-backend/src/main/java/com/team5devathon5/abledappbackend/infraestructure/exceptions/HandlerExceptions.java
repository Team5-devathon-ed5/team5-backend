package com.team5devathon5.abledappbackend.infraestructure.exceptions;

import com.team5devathon5.abledappbackend.infraestructure.messages.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Objects;

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        String errorMessage = ex.getMostSpecificCause().getMessage();

        if (errorMessage.contains("email exist")) {
            return new ResponseEntity<>(new ApiResponse("This Email is already use"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ApiResponse("Error processing the information check that the fields are not empty"), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> fieldBlankException (MethodArgumentNotValidException ex){
        String fieldError = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField();
        String messageError = "This field: "+ fieldError + ", cannot be empty.";
        ApiResponse response = new ApiResponse(messageError);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> userNotFoundException (RuntimeException userRun) {
        String errorMessage = userRun.getMessage();
        ResponseEntity<ApiResponse> response = null;

        if (errorMessage.contains("User not found")) {
            response = new ResponseEntity<>(new ApiResponse("User not found, please check your email or register at Able.com/register "), HttpStatus.NOT_FOUND);
        } else if (errorMessage.contains("validOpt expired")) {
            response = new ResponseEntity<>(new ApiResponse("Otp wrong or expired"), HttpStatus.BAD_REQUEST);
        } else if (errorMessage.contains("Verification Failed")) {
            response = new ResponseEntity<>(new ApiResponse("Token invalid or expired"), HttpStatus.NOT_FOUND);
        } else if (errorMessage.contains("Email blocked")) {
            response = new ResponseEntity<>(new ApiResponse("Email blocked due to excessive requests"), HttpStatus.FORBIDDEN);
        } else if (errorMessage.contains("Alert email")) {
        response = new ResponseEntity<>(new ApiResponse("Alert the email will be blocked in the next request"), HttpStatus.OK);
        }
        return response;
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse>badCredentials(BadCredentialsException bad){
        String errorMessage = bad.getMessage();
        ResponseEntity<ApiResponse> response = null;

        if (errorMessage.contains("Password Incorrect")){
            response = new ResponseEntity<>(new ApiResponse("Password Incorrect, please check your password"),HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
