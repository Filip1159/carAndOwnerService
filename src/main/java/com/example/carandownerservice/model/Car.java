package com.example.carandownerservice.model;

import lombok.*;

@Data
@AllArgsConstructor
public class Car {
    private final int id;
    private String model;
    private int year;
}
