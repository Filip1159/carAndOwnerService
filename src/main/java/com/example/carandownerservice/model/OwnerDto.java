package com.example.carandownerservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record OwnerDto(
        int id,
        String name,
        String surname,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date dateOfBirth,
        boolean isPremiumCustomer,
        List<Integer> carIds
) {
    public static OwnerDto from(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getSurname(), owner.getDateOfBirth(),
                owner.isPremiumCustomer(), owner.getCars().stream().map(Car::getId).toList());
    }
}
