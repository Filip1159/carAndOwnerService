package com.example.carandownerservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record InspectionDto(
        int id,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date date,
        int mileage,
        String comments,
        boolean isPositive,
        Integer carId
) {
    public static InspectionDto from(Inspection inspection) {
        return new InspectionDto(
                inspection.getId(),
                inspection.getDate(),
                inspection.getMileage(),
                inspection.getComments(),
                inspection.isPositive(),
                inspection.getCar() != null ? inspection.getCar().getId() : null);
    }
}
