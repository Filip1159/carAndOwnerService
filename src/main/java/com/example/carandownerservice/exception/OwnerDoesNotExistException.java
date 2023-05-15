package com.example.carandownerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class OwnerDoesNotExistException extends RuntimeException {
    public OwnerDoesNotExistException(int authorId) {
        super("Owner with id: [" + authorId + "] does not exist");
    }
}
