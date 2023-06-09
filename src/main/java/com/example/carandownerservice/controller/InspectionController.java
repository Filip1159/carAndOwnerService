package com.example.carandownerservice.controller;

import com.example.carandownerservice.model.InspectionDto;
import com.example.carandownerservice.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/inspection")
public class InspectionController {
    private final InspectionService inspectionService;

    @GetMapping
    List<InspectionDto> getAll() {
        return inspectionService.getAll().stream().map(InspectionDto::from).toList();
    }

    @GetMapping("/{id}")
    InspectionDto getById(@PathVariable int id) {
        return InspectionDto.from(inspectionService.getById(id));
    }

    @PostMapping
    ResponseEntity<InspectionDto> create(@RequestBody InspectionDto inspectionDto) {
        var createdInspection = inspectionService.create(inspectionDto);
        return ResponseEntity.status(CREATED).body(InspectionDto.from(createdInspection));
    }

    @PutMapping("/{id}")
    InspectionDto update(@PathVariable int id, @RequestBody InspectionDto inspectionDto) {
        return InspectionDto.from(inspectionService.update(id, inspectionDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        inspectionService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
