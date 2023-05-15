package com.example.carandownerservice.controller;

import com.example.carandownerservice.model.Owner;
import com.example.carandownerservice.model.OwnerDto;
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
    List<Owner> getAll() {
        return ownerService.getAll();
    }

    @GetMapping("/car/{carId}")
    List<Owner> getByCarId(@PathVariable int carId) {
        return ownerService.getByCarId(carId);
    }

    @PostMapping
    ResponseEntity<Owner> create(@RequestBody OwnerDto ownerDto) {
        ownerDto.carIds().forEach(carService::getCar);  // validate all books exist
        return ResponseEntity.status(CREATED).body(ownerService.create(ownerDto));
    }

    @PutMapping("/{id}")
    Owner update(@PathVariable int id, @RequestBody OwnerDto ownerDto) {
        ownerDto.carIds().forEach(carService::getCar);  // validate all books exist
        return ownerService.update(id, ownerDto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        ownerService.delete(id);
    }
}
