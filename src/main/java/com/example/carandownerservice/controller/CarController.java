package com.example.carandownerservice.controller;

import com.example.carandownerservice.model.Car;
import com.example.carandownerservice.model.Owner;
import com.example.carandownerservice.model.CarRequest;
import com.example.carandownerservice.model.CarWithOwners;
import com.example.carandownerservice.service.ICarService;
import com.example.carandownerservice.service.IOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {
    private final ICarService carService;
    private final IOwnerService ownerService;

    @GetMapping
    Collection<CarWithOwners> getBooks() {
        return carService.getCarsWithOwners();
    }

    @GetMapping("/{id}")
    Car getCar(@PathVariable("id") int id) {
        return carService.getCar(id);
    }

    @GetMapping("/author/{ownerId}")
    List<Car> getByAuthorId(@PathVariable int ownerId) {
        return carService.getByOwnerId(ownerId);
    }

    @PostMapping
    ResponseEntity<CarWithOwners> create(@RequestBody CarRequest carRequest) {
        carRequest.ownerIds().forEach(ownerService::getById); // validate that all authors exist
        var createdCar = carService.create(carRequest);
        var response = new CarWithOwners(
                createdCar.getId(),
                createdCar.getModel(),
                ownerService.getByCarId(createdCar.getId()).stream().map(Owner::getId).toList(),
                createdCar.getYear()
        );
        return ResponseEntity.status(CREATED).body(response);
    }

    @PutMapping("/{id}")
    CarWithOwners update(@PathVariable int id, @RequestBody CarRequest carRequest) {
        carRequest.ownerIds().forEach(ownerService::getById);  // check if all authors exist
        var updatedCar = carService.update(id, carRequest);
        return new CarWithOwners(
                updatedCar.getId(),
                updatedCar.getModel(),
                ownerService.getByCarId(updatedCar.getId()).stream().map(Owner::getId).toList(),
                updatedCar.getYear()
        );
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        carService.delete(id);
    }
}
