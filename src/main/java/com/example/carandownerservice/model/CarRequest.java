package com.example.carandownerservice.model;

import java.util.Date;
import java.util.List;

public record CarRequest(
        String brand,
        String model,
        int year,
        Date firstRegistration,
        List<Integer> ownerIds
) {
}
