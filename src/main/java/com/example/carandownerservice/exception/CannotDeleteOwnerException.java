package com.example.carandownerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class CannotDeleteOwnerException extends RuntimeException {
    public CannotDeleteOwnerException(int ownerId, List<Integer> carIds) {
        super("Cannot delete owner with id = [" + ownerId +
                "], because it is in relation with cars with ids: [" + carIds + "]");
    }
}
