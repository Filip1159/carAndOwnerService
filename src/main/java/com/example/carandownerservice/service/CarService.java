package com.example.carandownerservice.service;

import com.example.carandownerservice.exception.CarDoesNotExistException;
import com.example.carandownerservice.model.Car;
import com.example.carandownerservice.model.CarRequest;
import com.example.carandownerservice.repo.CarRepo;
import com.example.carandownerservice.repo.OwnerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepo carsRepo;
    private final OwnerRepo ownerRepo;

    public List<Car> getCars() {
        return carsRepo.findAll();
    }

    public Car getCar(int id) {
        return carsRepo.findById(id).orElseThrow(() -> new CarDoesNotExistException(id));
    }

    @Transactional
    public Car create(CarRequest carRequest) {
        var owners = ownerRepo.findAllById(carRequest.ownerIds());
        var carToSave = Car.builder()
                .brand(carRequest.brand())
                .model(carRequest.model())
                .year(carRequest.year())
                .firstRegistration(carRequest.firstRegistration())
                .owners(owners)
                .build();
        return carsRepo.save(carToSave);
    }

    @Transactional
    public Car update(int id, CarRequest carRequest) {
        var existingCar = getCar(id);
        existingCar.setBrand(carRequest.brand());
        existingCar.setModel(carRequest.model());
        existingCar.setYear(carRequest.year());
        existingCar.setFirstRegistration(carRequest.firstRegistration());
        var owners = ownerRepo.findAllById(carRequest.ownerIds());
        existingCar.setOwners(owners);
        carsRepo.save(existingCar);
        return existingCar;
    }

    public void delete(int id) {
        carsRepo.deleteById(id);
    }
}
