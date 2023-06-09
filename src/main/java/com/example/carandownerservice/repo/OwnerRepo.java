package com.example.carandownerservice.repo;

import com.example.carandownerservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Integer> {
}
