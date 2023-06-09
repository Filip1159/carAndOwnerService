package com.example.carandownerservice.service;

import com.example.carandownerservice.exception.CarDoesNotExistException;
import com.example.carandownerservice.model.Car;
import com.example.carandownerservice.model.CarDto;
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
    public Car create(CarDto carDto) {
        var owners = ownerRepo.findAllById(carDto.ownerIds());
        var carToSave = Car.builder()
                .brand(carDto.brand())
                .model(carDto.model())
                .year(carDto.year())
                .firstRegistration(carDto.firstRegistration())
                .owners(owners)
                .build();
        return carsRepo.save(carToSave);
    }

    @Transactional
    public Car update(int id, CarDto carDto) {
        var existingCar = getCar(id);
        existingCar.setBrand(carDto.brand());
        existingCar.setModel(carDto.model());
        existingCar.setYear(carDto.year());
        existingCar.setFirstRegistration(carDto.firstRegistration());
        var owners = ownerRepo.findAllById(carDto.ownerIds());
        existingCar.setOwners(owners);
        carsRepo.save(existingCar);
        return existingCar;
    }

    public void delete(int id) {
        carsRepo.deleteById(id);
    }
}
