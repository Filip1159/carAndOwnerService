package com.example.carandownerservice.controller;

import com.example.carandownerservice.model.Owner;
import com.example.carandownerservice.model.OwnerDto;
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
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;
    private final CarService carService;

    @GetMapping("/{id}")
    Owner getById(@PathVariable int id) {
        return ownerService.getById(id);
    }

    @GetMapping
    List<Owner> getAll() {
        return ownerService.getAll();
    }

    @PostMapping
    ResponseEntity<Owner> create(@RequestBody OwnerDto ownerDto) {
        ownerDto.carIds().forEach(carService::getCar);  // validate all books exist
        var createdOwner = ownerService.create(ownerDto);
        return ResponseEntity.status(CREATED).body(createdOwner);
    }

    @PutMapping("/{id}")
    Owner update(@PathVariable int id, @RequestBody OwnerDto ownerDto) {
        ownerDto.carIds().forEach(carService::getCar);  // validate all books exist
        return ownerService.update(id, ownerDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        ownerService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
