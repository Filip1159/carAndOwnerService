package com.example.carandownerservice.model;

import java.util.List;

public record OwnerDto(
        String name,
        String surname,
        List<Integer> carIds
) {
}
