package com.example.carandownerservice.service;

import com.example.carandownerservice.exception.OwnerDoesNotExistException;
import com.example.carandownerservice.model.Owner;
import com.example.carandownerservice.model.OwnerDto;
import com.example.carandownerservice.repo.CarRepo;
import com.example.carandownerservice.repo.OwnerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepo ownerRepo;
    private final CarRepo carRepo;

    public Owner getById(int ownerId) {
        return ownerRepo.findById(ownerId).orElseThrow(() -> new OwnerDoesNotExistException(ownerId));
    }

    public List<Owner> getAll() {
        return ownerRepo.findAll();
    }

    @Transactional
    public Owner create(OwnerDto ownerDto) {
        var cars = carRepo.findAllById(ownerDto.carIds());
        var ownerToSave = Owner.builder()
                .name(ownerDto.name())
                .surname(ownerDto.surname())
                .dateOfBirth(ownerDto.dateOfBirth())
                .isPremiumCustomer(ownerDto.isPremiumCustomer())
                .cars(cars)
                .build();
        return ownerRepo.save(ownerToSave);
    }

    public Owner update(int id, OwnerDto ownerDto) {
        var existingOwner = getById(id);
        existingOwner.setName(ownerDto.name());
        existingOwner.setSurname(ownerDto.surname());
        existingOwner.setDateOfBirth(ownerDto.dateOfBirth());
        existingOwner.setPremiumCustomer(ownerDto.isPremiumCustomer());
        var cars = carRepo.findAllById(ownerDto.carIds());
        existingOwner.setCars(cars);
        ownerRepo.save(existingOwner);
        return existingOwner;
    }

    public void delete(int id) {
        ownerRepo.deleteById(id);
    }
}
