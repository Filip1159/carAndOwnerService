package com.example.carandownerservice.service;

import com.example.carandownerservice.model.Car;
import com.example.carandownerservice.model.CarRequest;
import com.example.carandownerservice.model.CarWithOwners;

import java.util.Collection;
import java.util.List;

public interface ICarService {
    Collection<Car> getCars();

    Collection<CarWithOwners> getCarsWithOwners();

    Car getCar(int id);
    List<Car> getByOwnerId(int ownerId);
    Car create(CarRequest carRequest);
    Car update(int id, CarRequest carRequest);
    void delete(int id);
}
