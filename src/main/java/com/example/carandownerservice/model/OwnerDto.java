package com.example.carandownerservice.model;

import java.util.Date;
import java.util.List;

public record OwnerDto(
        String name,
        String surname,
        Date dateOfBirth,
        boolean isPremiumCustomer,
        List<Integer> carIds
) {
}
