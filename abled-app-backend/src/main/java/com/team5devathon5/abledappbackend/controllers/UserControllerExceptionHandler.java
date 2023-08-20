package com.team5devathon5.abledappbackend.controllers;

import com.team5devathon5.abledappbackend.infraestructure.exceptions.IdNotFoundException;
import com.team5devathon5.abledappbackend.infraestructure.messages.ApiError;
import com.team5devathon5.abledappbackend.utilities.Tables;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class UserControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IdNotFoundException.class})
    public ResponseEntity<ApiError> handlerIdNotFoundException(RuntimeException exception, WebRequest request) {
        ApiError apiError = createApiError(request);
        apiError.setMessage(String.format("Register not found in %s", Tables.users.name()));
        apiError.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

    public ApiError createApiError(WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setInstant(Instant.now());
        apiError.setPath(request.getContextPath());
        return apiError;
    }

}