package com.example.carandownerservice.service;

import com.example.carandownerservice.exception.CarDoesNotExistException;
import com.example.carandownerservice.model.Car;
import com.example.carandownerservice.model.CarRequest;
import com.example.carandownerservice.model.CarWithOwners;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {
    private static final List<Car> carsRepo = new ArrayList<>();
    private final IOwnershipService ownershipService;

    static {
        carsRepo.add(new Car(1, "BMW x5", 2013));
        carsRepo.add(new Car(2, "Fiat Panda", 2007));
        carsRepo.add(new Car(3, "Mercedes-Benz S600", 2018));
        carsRepo.add(new Car(5, "Ford Focus", 2013));
        carsRepo.add(new Car(6, "Honda Civic", 2006));
        carsRepo.add(new Car(7, "Volvo XC70", 2022));
        carsRepo.add(new Car(8, "Fiat Seicento", 1995));
        carsRepo.add(new Car(9, "Opel Astra", 2000));
        carsRepo.add(new Car(10, "Jeep Grand Cherokee", 2005));
        carsRepo.add(new Car(11, "Skoda Octavia", 2019));
        carsRepo.add(new Car(12, "Audi A4", 2023));
        carsRepo.add(new Car(13, "Hyundai i30", 2015));
        carsRepo.add(new Car(14, "Opel Corsa", 1999));
    }
    @Override
    public Collection<Car> getCars() {
        return carsRepo;
    }

    @Override
    public Collection<CarWithOwners> getCarsWithOwners() {
        return carsRepo.stream()
                .map(car -> new CarWithOwners(
                        car.getId(),
                        car.getModel(),
                        ownershipService.getOwnerIdsForCarId(car.getId()),
                        car.getYear()))
                .toList();
    }

    @Override
    public Car getCar(int id) {
        return carsRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElseThrow(() -> new CarDoesNotExistException(id));
    }

    @Override
    public List<Car> getByOwnerId(int ownerId) {
        var carIdsForOwner = ownershipService.getCarIdsForOwnerId(ownerId);
        return carIdsForOwner.stream()
                .map(this::getCar)
                .toList();
    }

    @Override
    public Car create(CarRequest carRequest) {
        var newCarId = nextId();
        var newCar = new Car(newCarId, carRequest.model(), carRequest.year());
        carsRepo.add(newCar);
        carRequest.ownerIds().forEach(id -> ownershipService.saveNewOwnership(id, newCarId));
        return newCar;
    }

    @Override
    public Car update(int id, CarRequest carRequest) {
        var existingCar = getCar(id);
        existingCar.setModel(carRequest.model());
        var ownersOfExistingCar = ownershipService.getOwnerIdsForCarId(id);
        carRequest.ownerIds().stream()  // add new authors
                .filter(ownerId -> !ownersOfExistingCar.contains(ownerId))
                .forEach(ownerId -> ownershipService.saveNewOwnership(ownerId, id));
        ownersOfExistingCar.stream()  // delete old authors
                .filter(ownerId -> !carRequest.ownerIds().contains(ownerId))
                .forEach(ownerId -> ownershipService.deleteOwnership(ownerId, id));
        existingCar.setYear(carRequest.year());
        return existingCar;
    }

    @Override
    public void delete(int id) {
        var existingCar = getCar(id);
        ownershipService.getOwnerIdsForCarId(id).forEach(
                authorId -> ownershipService.deleteOwnership(authorId, id)
        );
        carsRepo.remove(existingCar);
    }

    private int nextId() {
        return carsRepo.stream()
                .map(Car::getId)
                .max(Comparator.comparingInt(id -> id))
                .orElse(0) + 1;
    }
}
