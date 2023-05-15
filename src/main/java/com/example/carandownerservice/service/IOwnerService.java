package com.example.carandownerservice.service;

import com.example.carandownerservice.model.Owner;
import com.example.carandownerservice.model.OwnerDto;

import java.util.List;

public interface IOwnerService {
    Owner getById(int ownerId);
    List<Owner> getAll();
    Owner create(OwnerDto ownerDto);
    Owner update(int id, OwnerDto ownerDto);
    void delete(int id);
    List<Owner> getByCarId(int carId);
}
