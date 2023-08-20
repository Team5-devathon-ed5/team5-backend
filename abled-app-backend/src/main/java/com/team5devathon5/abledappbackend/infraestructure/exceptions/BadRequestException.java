package com.team5devathon5.abledappbackend.infraestructure.exceptions;

public class BadRequestException extends RuntimeException {

    private static final String ERROR_MESSAGE = "No valid Request for %s";

    public BadRequestException(String tablename) {
        super(String.format(ERROR_MESSAGE, tablename));
    }
}
