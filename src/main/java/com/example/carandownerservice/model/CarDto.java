package com.example.carandownerservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record CarDto(
        int id,
        String brand,
        String model,
        int year,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date firstRegistration,
        List<Integer> ownerIds
) {
    public static CarDto from(Car car) {
        return new CarDto(car.getId(), car.getBrand(), car.getModel(), car.getYear(), car.getFirstRegistration(),
                car.getOwners().stream().map(Owner::getId).toList());
    }
}
