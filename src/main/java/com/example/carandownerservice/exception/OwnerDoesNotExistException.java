package com.example.carandownerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class OwnerDoesNotExistException extends RuntimeException {
    public OwnerDoesNotExistException(int ownerId) {
        super("Owner with id: [" + ownerId + "] does not exist");
    }
}
