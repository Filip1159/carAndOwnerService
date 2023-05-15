package com.example.carandownerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class OwnershipAlreadyExistsException extends RuntimeException {
    public OwnershipAlreadyExistsException(String message) {
        super(message);
    }
}
