package com.example.carandownerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ownership {
    private int ownerId;
    private int carId;
}
