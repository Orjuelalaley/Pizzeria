package com.orjuelalaley.Pizzeria.Exception;

import org.springframework.http.HttpStatus;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String message) {
        super(message);
    }
}
