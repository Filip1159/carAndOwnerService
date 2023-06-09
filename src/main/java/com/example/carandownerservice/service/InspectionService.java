package com.example.carandownerservice.service;

import com.example.carandownerservice.exception.CarDoesNotExistException;
import com.example.carandownerservice.exception.InspectionDoesNotExistException;
import com.example.carandownerservice.model.Inspection;
import com.example.carandownerservice.model.InspectionDto;
import com.example.carandownerservice.repo.CarRepo;
import com.example.carandownerservice.repo.InspectionRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectionService {
    private final InspectionRepo inspectionRepo;
    private final CarRepo carRepo;

    public Inspection getById(int id) {
        return inspectionRepo.findById(id).orElseThrow(() -> new InspectionDoesNotExistException(id));
    }

    public List<Inspection> getAll() {
        return inspectionRepo.findAll();
    }

    @Transactional
    public Inspection create(InspectionDto inspectionDto) {
        var car = carRepo.findById(inspectionDto.carId())
                .orElseThrow(() -> new CarDoesNotExistException(inspectionDto.carId()));
        var inspectionToSave = Inspection.builder()
                .date(inspectionDto.date())
                .mileage(inspectionDto.mileage())
                .comments(inspectionDto.comments())
                .isPositive(inspectionDto.isPositive())
                .car(car)
                .build();
        return inspectionRepo.save(inspectionToSave);
    }

    @Transactional
    public Inspection update(int id, InspectionDto inspectionDto) {
        var existingInspection = getById(id);
        existingInspection.setDate(inspectionDto.date());
        existingInspection.setComments(inspectionDto.comments());
        existingInspection.setMileage(inspectionDto.mileage());
        existingInspection.setPositive(inspectionDto.isPositive());
        var car = carRepo.findById(inspectionDto.carId())
                .orElseThrow(() -> new CarDoesNotExistException(inspectionDto.carId()));
        existingInspection.setCar(car);
        return inspectionRepo.save(existingInspection);
    }

    public void delete(int id) {
        inspectionRepo.deleteById(id);
    }
}
