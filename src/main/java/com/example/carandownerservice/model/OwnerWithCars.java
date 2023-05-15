package com.example.carandownerservice.model;

import java.util.List;

public record OwnerWithCars(
        int id,
        String name,
        String surname,
        List<Integer> carIds
) {
}
