package com.nifemi.farmcollector.service;

import com.farmcollector.dto.HarvestedCropRequestDTO;
import com.farmcollector.dto.HarvestedCropResponseDTO;
import com.farmcollector.entity.Harvested;
import com.farmcollector.entity.Planted;
import com.farmcollector.exception.ResourceNotFoundException;
import com.farmcollector.repository.HarvestedRepository;
import com.farmcollector.repository.PlantedRepository;
import com.farmcollector.service.HarvestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestedServiceImpl implements HarvestedService {

    private final HarvestedRepository harvestedRepository;
    private final PlantedRepository plantedRepository;

    @Autowired
    public HarvestedServiceImpl(HarvestedRepository harvestedRepository, PlantedRepository plantedRepository) {
        this.harvestedRepository = harvestedRepository;
        this.plantedRepository = plantedRepository;
    }

    @Override
    public HarvestedCropResponseDTO createHarvested(HarvestedCropRequestDTO dto) {
        Planted planted = plantedRepository.findById(dto.getPlantedCropId())
                .orElseThrow(() -> new ResourceNotFoundException("Planted crop not found with id: " + dto.getPlantedCropId()));

        Harvested harvested = new Harvested();
        harvested.setActualAmount(dto.getActualAmount());
        harvested.setPlanted(planted);

        Harvested saved = harvestedRepository.save(harvested);

        return mapToResponseDTO(saved);
    }

    @Override
    public HarvestedCropResponseDTO getHarvestedById(Long id) {
        Harvested harvested = harvestedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvested record not found with id: " + id));
        return mapToResponseDTO(harvested);
    }

    @Override
    public List<HarvestedCropResponseDTO> getAllHarvests() {
        return harvestedRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestedCropResponseDTO updateHarvested(Long id, HarvestedCropRequestDTO dto) {
        Harvested harvested = harvestedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvested record not found with id: " + id));

        harvested.setActualAmount(dto.getActualAmount());

        if (!harvested.getPlanted().getId().equals(dto.getPlantedCropId())) {
            Planted planted = plantedRepository.findById(dto.getPlantedCropId())
                    .orElseThrow(() -> new ResourceNotFoundException("Planted crop not found with id: " + dto.getPlantedCropId()));
            harvested.setPlanted(planted);
        }

        Harvested updated = harvestedRepository.save(harvested);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteHarvested(Long id) {
        Harvested harvested = harvestedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvested record not found with id: " + id));
        harvestedRepository.delete(harvested);
    }

    private HarvestedCropResponseDTO mapToResponseDTO(Harvested harvested) {
        HarvestedCropResponseDTO dto = new HarvestedCropResponseDTO();
        dto.setId(harvested.getId());
        dto.setPlantedCropId(harvested.getPlanted().getId());
        dto.setActualAmount(harvested.getActualAmount());
        return dto;
    }
}

