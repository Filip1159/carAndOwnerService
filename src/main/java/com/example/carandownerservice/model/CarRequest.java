package com.example.carandownerservice.model;

import java.util.List;

public record CarRequest(
        String model,
        int year,
        List<Integer> ownerIds
) {
}
