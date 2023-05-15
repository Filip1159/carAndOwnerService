package com.example.carandownerservice.controller;

import com.example.carandownerservice.model.Car;
import com.example.carandownerservice.model.Owner;
import com.example.carandownerservice.model.OwnerDto;
import com.example.carandownerservice.model.OwnerWithCars;
import com.example.carandownerservice.service.ICarService;
import com.example.carandownerservice.service.IOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final IOwnerService ownerService;
    private final ICarService carService;

    @GetMapping("/{id}")
    Owner getById(@PathVariable int id) {
        return ownerService.getById(id);
    }

    @GetMapping
    List<OwnerWithCars> getAll() {
        return ownerService.getOwnersWithCars();
    }

    @GetMapping("/car/{carId}")
    List<Owner> getByCarId(@PathVariable int carId) {
        return ownerService.getByCarId(carId);
    }

    @PostMapping
    ResponseEntity<OwnerWithCars> create(@RequestBody OwnerDto ownerDto) {
        ownerDto.carIds().forEach(carService::getCar);  // validate all books exist
        var createdOwner = ownerService.create(ownerDto);
        var response = new OwnerWithCars(
                createdOwner.getId(),
                createdOwner.getName(),
                createdOwner.getSurname(),
                carService.getByOwnerId(createdOwner.getId()).stream().map(Car::getId).toList()
        );
        return ResponseEntity.status(CREATED).body(response);
    }

    @PutMapping("/{id}")
    OwnerWithCars update(@PathVariable int id, @RequestBody OwnerDto ownerDto) {
        ownerDto.carIds().forEach(carService::getCar);  // validate all books exist
        var updatedOwner = ownerService.update(id, ownerDto);
        return new OwnerWithCars(
                updatedOwner.getId(),
                updatedOwner.getName(),
                updatedOwner.getSurname(),
                carService.getByOwnerId(updatedOwner.getId()).stream().map(Car::getId).toList()
        );
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        ownerService.delete(id);
    }
}
