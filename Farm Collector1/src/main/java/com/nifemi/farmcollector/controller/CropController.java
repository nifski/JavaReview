package com.nifemi.farmcollector.controller;

import com.farmcollector.dto.CropDetailsDTO;
import com.farmcollector.entity.Crop;
import com.farmcollector.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    private final CropService cropService;

    @Autowired
    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping
    public ResponseEntity<Crop> createCrop(@RequestBody Crop crop) {
        Crop created = cropService.createCrop(crop);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropDetailsDTO> getCropDetails(@PathVariable Long id) {
        return ResponseEntity.ok(cropService.getCropDetails(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Crop>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(cropService.findCropsByName(name));
    }

    @GetMapping("/by-farm/{farmId}")
    public ResponseEntity<List<Crop>> getCropsByFarm(@PathVariable Long farmId) {
        return ResponseEntity.ok(cropService.getCropsByFarmId(farmId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}