package com.nifemi.farmcollector.controller;

import com.farmcollector.dto.HarvestedCropRequestDTO;
import com.farmcollector.dto.HarvestedCropResponseDTO;
import com.farmcollector.service.HarvestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvested")
public class HarvestedController {

    private final HarvestedService harvestedService;

    @Autowired
    public HarvestedController(HarvestedService harvestedService) {
        this.harvestedService = harvestedService;
    }

    @PostMapping
    public ResponseEntity<HarvestedCropResponseDTO> submitHarvestedData(@RequestBody HarvestedCropRequestDTO dto) {
        HarvestedCropResponseDTO created = harvestedService.createHarvested(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestedCropResponseDTO> getHarvestedById(@PathVariable Long id) {
        HarvestedCropResponseDTO harvested = harvestedService.getHarvestedById(id);
        return ResponseEntity.ok(harvested);
    }

    @GetMapping
    public ResponseEntity<List<HarvestedCropResponseDTO>> getAllHarvests() {
        List<HarvestedCropResponseDTO> allHarvests = harvestedService.getAllHarvests();
        return ResponseEntity.ok(allHarvests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestedCropResponseDTO> updateHarvested(@PathVariable Long id, @RequestBody HarvestedCropRequestDTO dto) {
        HarvestedCropResponseDTO updated = harvestedService.updateHarvested(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvested(@PathVariable Long id) {
        harvestedService.deleteHarvested(id);
        return ResponseEntity.noContent().build();
    }
}
