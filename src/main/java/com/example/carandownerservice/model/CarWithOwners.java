package com.example.carandownerservice.model;

import java.util.List;

public record CarWithOwners(
        int id,
        String model,
        List<Integer> ownerIds,
        int year
) {
}
