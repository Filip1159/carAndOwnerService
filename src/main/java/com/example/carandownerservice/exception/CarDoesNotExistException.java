package com.example.carandownerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class CarDoesNotExistException extends RuntimeException {
    public CarDoesNotExistException(int carId) {
        super("Car with id: [" + carId + "] does not exist");
    }
}
