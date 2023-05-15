package com.example.carandownerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class UnknownOwnerException extends RuntimeException {
    public UnknownOwnerException(int authorId) {
        super("Owner with provided id does not exist: " + authorId);
    }
}
