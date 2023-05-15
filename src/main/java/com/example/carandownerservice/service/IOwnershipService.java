package com.example.carandownerservice.service;

import java.util.List;

public interface IOwnershipService {
    List<Integer> getCarIdsForOwnerId(int ownerId);

    List<Integer> getOwnerIdsForCarId(int carId);

    void saveNewOwnership(int ownerId, int carId);

    void deleteOwnership(int ownerId, int carId);
}
