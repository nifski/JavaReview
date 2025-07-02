package com.farmcollector.service;

import com.farmcollector.dto.PlantedDetailsDTO;
import java.util.List;

public interface PlantedService {
    PlantedDetailsDTO createPlanted(PlantedDetailsDTO plantedDetailsDTO);
    PlantedDetailsDTO getPlantedById(Long id);
    List<PlantedDetailsDTO> getAllPlantings();
    PlantedDetailsDTO updatePlanted(Long id, PlantedDetailsDTO plantedDetailsDTO);
    void deletePlanted(Long id);

}
