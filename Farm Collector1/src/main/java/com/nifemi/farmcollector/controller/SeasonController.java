package com.nifemi.farmcollector.controller;

import com.nifemi.farmcollector.dto.SeasonDTO;
import com.nifemi.farmcollector.service.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping
    public ResponseEntity<SeasonDTO> createSeason(@RequestBody SeasonDTO seasonDTO) {
        SeasonDTO createdSeason = seasonService.createSeason(seasonDTO);
        return new ResponseEntity<>(createdSeason, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SeasonDTO>> getAllSeasons() {
        List<SeasonDTO> seasons = seasonService.getAllSeasons();
        return ResponseEntity.ok(seasons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDTO> getSeasonById(@PathVariable Long id) {
        SeasonDTO season = seasonService.getSeasonById(id);
        return ResponseEntity.ok(season);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeasonDTO> updateSeason(@PathVariable Long id, @RequestBody SeasonDTO seasonDTO) {
        SeasonDTO updatedSeason = seasonService.updateSeason(id, seasonDTO);
        return ResponseEntity.ok(updatedSeason);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {
        seasonService.deleteSeason(id);
        return ResponseEntity.noContent().build();
    }
}