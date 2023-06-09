package com.example.carandownerservice.controller;

import com.example.carandownerservice.model.Car;
import com.example.carandownerservice.model.CarRequest;
import com.example.carandownerservice.service.CarService;
import com.example.carandownerservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final OwnerService ownerService;

    @GetMapping
    List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    Car getCar(@PathVariable("id") int id) {
        return carService.getCar(id);
    }

    @PostMapping
    ResponseEntity<Car> create(@RequestBody CarRequest carRequest) {
        carRequest.ownerIds().forEach(ownerService::getById); // validate that all authors exist
        var createdCar = carService.create(carRequest);
        return ResponseEntity.status(CREATED).body(createdCar);
    }

    @PutMapping("/{id}")
    Car update(@PathVariable int id, @RequestBody CarRequest carRequest) {
        carRequest.ownerIds().forEach(ownerService::getById);  // check if all authors exist
        return carService.update(id, carRequest);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        carService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
