package com.example.carandownerservice.service;

import com.example.carandownerservice.exception.OwnershipAlreadyExistsException;
import com.example.carandownerservice.model.Ownership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnershipService implements IOwnershipService {
    private static final List<Ownership> ownershipRepo = new ArrayList<>();

    static {
        ownershipRepo.add(new Ownership(1, 3));
        ownershipRepo.add(new Ownership(2, 2));
        ownershipRepo.add(new Ownership(7, 2));
        ownershipRepo.add(new Ownership(8, 2));
        ownershipRepo.add(new Ownership(3, 1));
        ownershipRepo.add(new Ownership(8, 1));
        ownershipRepo.add(new Ownership(9, 14));
        ownershipRepo.add(new Ownership(10, 14));
        ownershipRepo.add(new Ownership(1, 5));
        ownershipRepo.add(new Ownership(3, 14));
        ownershipRepo.add(new Ownership(3, 6));
        ownershipRepo.add(new Ownership(6, 6));
        ownershipRepo.add(new Ownership(4, 7));
        ownershipRepo.add(new Ownership(2, 7));
        ownershipRepo.add(new Ownership(3, 8));
        ownershipRepo.add(new Ownership(4, 8));
        ownershipRepo.add(new Ownership(10, 9));
        ownershipRepo.add(new Ownership(8, 9));
        ownershipRepo.add(new Ownership(3, 9));
        ownershipRepo.add(new Ownership(5, 10));
        ownershipRepo.add(new Ownership(6, 11));
        ownershipRepo.add(new Ownership(8, 12));
        ownershipRepo.add(new Ownership(7, 12));
        ownershipRepo.add(new Ownership(8, 13));
    }

    @Override
    public List<Integer> getCarIdsForOwnerId(int ownerId) {
        return ownershipRepo.stream()
                .filter(ownership -> ownership.getOwnerId() == ownerId)
                .map(Ownership::getCarId)
                .toList();
    }

    @Override
    public List<Integer> getOwnerIdsForCarId(int carId) {
        return ownershipRepo.stream()
                .filter(ownership -> ownership.getCarId() == carId)
                .map(Ownership::getOwnerId)
                .toList();
    }

    @Override
    public void saveNewOwnership(int ownerId, int carId) {
        var newOwnership = new Ownership(ownerId, carId);
        if (ownershipRepo.contains(newOwnership))
            throw new OwnershipAlreadyExistsException(
                    "Owner with id: [" + ownerId + "] already is an owner of car with id: [" + carId + "]");
        ownershipRepo.add(newOwnership);
    }

    @Override
    public void deleteOwnership(int ownerId, int carId) {
        var ownership = new Ownership(ownerId, carId);
        ownershipRepo.remove(ownership);
    }
}
