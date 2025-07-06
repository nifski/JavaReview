package com.nifemi.farmcollector.controller;

import com.nifemi.farmcollector.dto.PlantedDetailsDTO;
import com.nifemi.farmcollector.service.PlantedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planted")
public class PlantedController {

    private final PlantedService plantedService;

    public PlantedController(PlantedService plantedService) {
        this.plantedService = plantedService;
    }

    @PostMapping
    public ResponseEntity<PlantedDetailsDTO> submitPlantedData(@RequestBody PlantedDetailsDTO dto) {
        PlantedDetailsDTO saved = plantedService.createPlanted(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlantedDetailsDTO>> getAllPlanted() {
        List<PlantedDetailsDTO> plantings = plantedService.getAllPlantings();
        return ResponseEntity.ok(plantings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantedDetailsDTO> getPlantedById(@PathVariable Long id) {
        PlantedDetailsDTO planted = plantedService.getPlantedById(id);
        return ResponseEntity.ok(planted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantedDetailsDTO> updatePlanted(@PathVariable Long id, @RequestBody PlantedDetailsDTO dto) {
        PlantedDetailsDTO updated = plantedService.updatePlanted(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanted(@PathVariable Long id) {
        plantedService.deletePlanted(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<PlantedDetailsDTO>> getByFarm(@PathVariable Long farmId) {
        List<PlantedDetailsDTO> plantings = plantedService.findByFarmId(farmId);
        return ResponseEntity.ok(plantings);
    }

    @GetMapping("/season/{seasonId}")
    public ResponseEntity<List<PlantedDetailsDTO>> getBySeason(@PathVariable Long seasonId) {
        List<PlantedDetailsDTO> plantings = plantedService.findBySeasonId(seasonId);
        return ResponseEntity.ok(plantings);
    }
}
