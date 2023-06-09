package com.example.carandownerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class InspectionDoesNotExistException extends RuntimeException {
    public InspectionDoesNotExistException(int inspectionId) {
        super("Inspection with id: [" + inspectionId + "] does not exist");
    }
}
