package com.nifemi.farmcollector.service;

import com.farmcollector.dto.PlantedDetailsDTO;
import com.farmcollector.entity.Planted;
import com.farmcollector.exception.ResourceNotFoundException;
import com.farmcollector.repository.PlantedRepository;
import com.farmcollector.service.PlantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantedServiceImpl implements PlantedService {

    private final PlantedRepository plantedRepository;

    @Autowired
    public PlantedServiceImpl(PlantedRepository plantedRepository) {
        this.plantedRepository = plantedRepository;
    }

    @Override
    public PlantedDetailsDTO createPlanted(PlantedDetailsDTO dto) {
        Planted planted = mapToEntity(dto);
        Planted saved = plantedRepository.save(planted);
        return mapToDTO(saved);
    }

    @Override
    public PlantedDetailsDTO getPlantedById(Long id) {
        Planted planted = plantedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planted record not found with id: " + id));
        return mapToDTO(planted);
    }

    @Override
    public List<PlantedDetailsDTO> getAllPlantings() {
        return plantedRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlantedDetailsDTO updatePlanted(Long id, PlantedDetailsDTO dto) {
        Planted planted = plantedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planted record not found with id: " + id));

        // Update fields
        planted.setExpectedAmount(dto.getExpectedAmount());
        planted.setPlantingArea(dto.getPlantingArea());
        // Update other fields if any...

        Planted updated = plantedRepository.save(planted);
        return mapToDTO(updated);
    }

    @Override
    public void deletePlanted(Long id) {
        Planted planted = plantedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planted record not found with id: " + id));
        plantedRepository.delete(planted);
    }

    @Override
    public List<PlantedDetailsDTO> findByCropId(Long cropId) {
        return plantedRepository.findByCropId(cropId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlantedDetailsDTO> findByFarmId(Long farmId) {
        return plantedRepository.findByFarmId(farmId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Utility methods to convert between Entity and DTO
    private PlantedDetailsDTO mapToDTO(Planted planted) {
        PlantedDetailsDTO dto = new PlantedDetailsDTO();
        dto.setId(planted.getId());
        dto.setExpectedAmount(planted.getExpectedAmount());
        dto.setPlantingArea(planted.getPlantingArea());
        dto.setSeason(planted.getSeason().getName());
        dto.setFarmId(planted.getFarm().getId());
        dto.setCropId(planted.getCrop().getId());
        // Add other fields as needed
        return dto;
    }

    private Planted mapToEntity(PlantedDetailsDTO dto) {
        Planted planted = new Planted();
        planted.setExpectedAmount(dto.getExpectedAmount());
        planted.setPlantingArea(dto.getPlantingArea());
        // You will need to fetch and set Season, Farm, Crop entities separately in your service/FarmController before calling this method or modify it accordingly
        return planted;
    }
}
