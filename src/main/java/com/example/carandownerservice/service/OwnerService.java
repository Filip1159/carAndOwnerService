package com.example.carandownerservice.service;

import com.example.carandownerservice.exception.OwnerDoesNotExistException;
import com.example.carandownerservice.model.Owner;
import com.example.carandownerservice.model.OwnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService implements IOwnerService {
    private static final List<Owner> ownerRepo = new ArrayList<>();
    private final IOwnershipService ownershipService;

    static {
        ownerRepo.add(new Owner(1, "Anna", "Kowalska"));
        ownerRepo.add(new Owner(2, "Stanisław", "Lewandowski"));
        ownerRepo.add(new Owner(3, "Henryk", "Rasiak"));
        ownerRepo.add(new Owner(4, "Wanda", "Szatkowski"));
        ownerRepo.add(new Owner(5, "Bolesław", "Pawłowicz"));
        ownerRepo.add(new Owner(6, "Aleksandra", "Olkowicz"));
        ownerRepo.add(new Owner(7, "Julian", "Wieczorek"));
        ownerRepo.add(new Owner(8, "Anita", "Garczewska"));
        ownerRepo.add(new Owner(9, "Hanna", "Mazur"));
        ownerRepo.add(new Owner(10, "Zygmunt", "Mazur"));
        ownerRepo.add(new Owner(11, "Zofia", "Kaźmierczak"));
        ownerRepo.add(new Owner(12, "Patrycja", "Cieślak"));
        ownerRepo.add(new Owner(13, "Rafał", "Dzikowicz"));
        ownerRepo.add(new Owner(14, "Tomasz", "Kozłowski"));
    }

    @Override
    public Owner getById(int ownerId) {
        return ownerRepo.stream()
                .filter(owner -> owner.getId() == ownerId)
                .findAny()
                .orElseThrow(() -> new OwnerDoesNotExistException(ownerId));
    }

    @Override
    public List<Owner> getAll() {
        return ownerRepo;
    }

    @Override
    public List<Owner> getByCarId(int carId) {
        var ownerIdsForCar = ownershipService.getOwnerIdsForCarId(carId);
        return ownerIdsForCar.stream()
                .map(this::getById)
                .toList();
    }

    @Override
    public Owner create(OwnerDto ownerDto) {
        var newOwnerId = nextId();
        var newOwner = new Owner(newOwnerId, ownerDto.name(), ownerDto.surname());
        ownerRepo.add(newOwner);
        ownerDto.carIds().forEach(bookId -> ownershipService.saveNewOwnership(newOwnerId, bookId));
        return newOwner;
    }

    @Override
    public Owner update(int id, OwnerDto ownerDto) {
        var existingOwner = getById(id);
        existingOwner.setName(ownerDto.name());
        existingOwner.setSurname(ownerDto.surname());
        var carsForExistingOwner = ownershipService.getCarIdsForOwnerId(id);
        ownerDto.carIds().stream()  // add new cars
                .filter(carId -> !carsForExistingOwner.contains(carId))
                .forEach(carId -> ownershipService.saveNewOwnership(id, carId));
        carsForExistingOwner.stream()  // delete old books
                .filter(carId -> !ownerDto.carIds().contains(carId))
                .forEach(carId -> ownershipService.deleteOwnership(id, carId));
        return existingOwner;
    }

    @Override
    public void delete(int id) {
        var existingOwner = getById(id);
        ownershipService.getCarIdsForOwnerId(id).forEach(
                carId -> ownershipService.deleteOwnership(id, carId)
        );
        ownerRepo.remove(existingOwner);
    }

    private int nextId() {
        return ownerRepo.stream()
                .map(Owner::getId)
                .max(Comparator.comparingInt(id -> id))
                .orElse(0) + 1;
    }
}
