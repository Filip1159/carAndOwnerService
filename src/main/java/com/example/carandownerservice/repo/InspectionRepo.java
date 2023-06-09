package com.example.carandownerservice.repo;

import com.example.carandownerservice.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepo extends JpaRepository<Inspection, Integer> {
}
