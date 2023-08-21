package com.team5devathon5.abledappbackend.infraestructure.exceptions;

import org.springframework.http.HttpStatus;

public class NoAuthorizedException extends RuntimeException {

    private static final String ERROR_MESSAGE = "No authorized for this information!";

    public NoAuthorizedException(String tablename) {

        super(String.format(ERROR_MESSAGE, tablename));

    }
}
