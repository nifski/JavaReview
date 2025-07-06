package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.PlantedDetailsDTO;
import java.util.List;

public interface PlantedService {
    PlantedDetailsDTO createPlanted(PlantedDetailsDTO plantedDetailsDTO);
    PlantedDetailsDTO getPlantedById(Long id);
    List<PlantedDetailsDTO> getAllPlantings();
    PlantedDetailsDTO updatePlanted(Long id, PlantedDetailsDTO plantedDetailsDTO);
    void deletePlanted(Long id);
    List<PlantedDetailsDTO> findByFarmId(Long farmId);
    List<PlantedDetailsDTO> findBySeasonId(Long seasonId);
    List<PlantedDetailsDTO> findByCropId(Long cropId);
}