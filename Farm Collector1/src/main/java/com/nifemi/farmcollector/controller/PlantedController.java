package com.nifemi.farmcollector.controller;

import com.farmcollector.dto.PlantedDetailsDTO;
import com.farmcollector.entity.Planted;
import com.farmcollector.service.PlantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planted")
public class PlantedController {

    private final PlantedService plantedService;

    @Autowired
    public PlantedController(PlantedService plantedService) {
        this.plantedService = plantedService;
    }

    @PostMapping
    public ResponseEntity<Planted> submitPlantedData(@RequestBody Planted planted) {
        Planted saved = plantedService.savePlanted(planted);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Planted>> getAllPlanted() {
        return ResponseEntity.ok(plantedService.getAllPlanted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantedDetailsDTO> getPlantedDetails(@PathVariable Long id) {
        return ResponseEntity.ok(plantedService.getPlantedDetails(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanted(@PathVariable Long id) {
        plantedService.deletePlanted(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-farm/{farmId}")
    public ResponseEntity<List<Planted>> getByFarm(@PathVariable Long farmId) {
        return ResponseEntity.ok(plantedService.findByFarmId(farmId));
    }

    @GetMapping("/by-season/{seasonId}")
    public ResponseEntity<List<Planted>> getBySeason(@PathVariable Long seasonId) {
        return ResponseEntity.ok(plantedService.findBySeasonId(seasonId));
    }
}

