package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.PlantedDetailsDTO;
import com.nifemi.farmcollector.entity.Crop;
import com.nifemi.farmcollector.entity.Farm;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.exception.ResourceNotFoundException;
import com.nifemi.farmcollector.repository.CropRepository;
import com.nifemi.farmcollector.repository.FarmRepository;
import com.nifemi.farmcollector.repository.PlantedRepository;
import com.nifemi.farmcollector.repository.SeasonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantedServiceImpl implements PlantedService {

    private final PlantedRepository plantedRepository;
    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;
    private final SeasonRepository seasonRepository;

    public PlantedServiceImpl(PlantedRepository plantedRepository, FarmRepository farmRepository,
                              CropRepository cropRepository, SeasonRepository seasonRepository) {
        this.plantedRepository = plantedRepository;
        this.farmRepository = farmRepository;
        this.cropRepository = cropRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public PlantedDetailsDTO createPlanted(PlantedDetailsDTO dto) {
        Farm farm = farmRepository.findById(dto.getFarmId())
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found with id: " + dto.getFarmId()));

        Crop crop = cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + dto.getCropId()));

        Season season = seasonRepository.findByName(dto.getSeasonName())
                .orElseThrow(() -> new ResourceNotFoundException("Season not found with name: " + dto.getSeasonName()));

        Planted planted = new Planted();
        planted.setFarm(farm);
        planted.setCrop(crop);
        planted.setSeason(season);
        planted.setPlantingArea(dto.getPlantingArea());
        planted.setExpectedYield(dto.getExpectedYield());
        planted.setPlantedDate(dto.getPlantedDate());

        Planted savedPlanted = plantedRepository.save(planted);
        return mapToDTO(savedPlanted);
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

        planted.setExpectedYield(dto.getExpectedYield());
        planted.setPlantingArea(dto.getPlantingArea());
        planted.setPlantedDate(dto.getPlantedDate());

        Planted updated = plantedRepository.save(planted);
        return mapToDTO(updated);
    }

    @Override
    public void deletePlanted(Long id) {
        if (!plantedRepository.existsById(id)) {
            throw new ResourceNotFoundException("Planted record not found with id: " + id);
        }
        plantedRepository.deleteById(id);
    }

    @Override
    public List<PlantedDetailsDTO> findByFarmId(Long farmId) {
        return plantedRepository.findByFarm_Id(farmId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlantedDetailsDTO> findBySeasonId(Long seasonId) {
        return plantedRepository.findBySeason_Id(seasonId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlantedDetailsDTO> findByCropId(Long cropId) {
        return plantedRepository.findByCrop_Id(cropId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PlantedDetailsDTO mapToDTO(Planted planted) {
        return new PlantedDetailsDTO(
                planted.getId(),
                planted.getFarm().getId(),
                planted.getCrop().getId(),
                planted.getSeason().getName(),
                planted.getPlantingArea(),
                planted.getExpectedYield(),
                planted.getPlantedDate()
        );
    }
}