package com.example.carandownerservice.controller;

import com.example.carandownerservice.model.CarDto;
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
    List<CarDto> getCars() {
        return carService.getCars().stream().map(CarDto::from).toList();
    }

    @GetMapping("/{id}")
    CarDto getCar(@PathVariable("id") int id) {
        return CarDto.from(carService.getCar(id));
    }

    @GetMapping("/count")
    int getCount() {
        return carService.getCars().size();
    }

    @PostMapping
    ResponseEntity<CarDto> create(@RequestBody CarDto carDto) {
        carDto.ownerIds().forEach(ownerService::getById); // validate that all authors exist
        var createdCar = carService.create(carDto);
        return ResponseEntity.status(CREATED).body(CarDto.from(createdCar));
    }

    @PutMapping("/{id}")
    CarDto update(@PathVariable int id, @RequestBody CarDto carDto) {
        carDto.ownerIds().forEach(ownerService::getById);  // check if all authors exist
        return CarDto.from(carService.update(id, carDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        carService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
