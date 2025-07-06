package com.nifemi.farmcollector.controller;

import com.nifemi.farmcollector.dto.CropDetailsDTO;
import com.nifemi.farmcollector.service.CropService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping
    public ResponseEntity<CropDetailsDTO> createCrop(@RequestBody CropDetailsDTO cropDetailsDTO) {
        CropDetailsDTO createdCrop = cropService.createCrop(cropDetailsDTO);
        return new ResponseEntity<>(createdCrop, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CropDetailsDTO>> getAllCrops() {
        List<CropDetailsDTO> crops = cropService.getAllCrops();
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropDetailsDTO> getCropById(@PathVariable Long id) {
        CropDetailsDTO crop = cropService.getCropById(id);
        return ResponseEntity.ok(crop);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CropDetailsDTO>> findCropsByName(@RequestParam String name) {
        List<CropDetailsDTO> crops = cropService.findCropsByFullName(name);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<CropDetailsDTO>> getCropsByFarm(@PathVariable Long farmId) {
        List<CropDetailsDTO> crops = cropService.findAllCropsByFarmId(farmId);
        return ResponseEntity.ok(crops);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropDetailsDTO> updateCrop(@PathVariable Long id, @RequestBody CropDetailsDTO cropDetailsDTO) {
        CropDetailsDTO updatedCrop = cropService.updateCrop(id, cropDetailsDTO);
        return ResponseEntity.ok(updatedCrop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}