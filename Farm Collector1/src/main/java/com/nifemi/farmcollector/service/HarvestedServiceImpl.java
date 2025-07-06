package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.HarvestedCropRequestDTO;
import com.nifemi.farmcollector.dto.HarvestedCropResponseDTO;
import com.nifemi.farmcollector.entity.Crop;
import com.nifemi.farmcollector.entity.Farm;
import com.nifemi.farmcollector.entity.Harvested;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.exception.ResourceNotFoundException;
import com.nifemi.farmcollector.repository.HarvestedRepository;
import com.nifemi.farmcollector.repository.PlantedRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestedServiceImpl implements HarvestedService {

    private final HarvestedRepository harvestedRepository;
    private final PlantedRepository plantedRepository;

    public HarvestedServiceImpl(HarvestedRepository harvestedRepository, PlantedRepository plantedRepository) {
        this.harvestedRepository = harvestedRepository;
        this.plantedRepository = plantedRepository;
    }

    @Override
    public HarvestedCropResponseDTO createHarvested(HarvestedCropRequestDTO dto) {
        Planted planted = plantedRepository.findById(dto.getPlantedCropId())
                .orElseThrow(() -> new ResourceNotFoundException("Planted crop not found with id: " + dto.getPlantedCropId()));

        Harvested harvested = new Harvested();
        harvested.setActualYield(dto.getActualAmount());
        harvested.setFarm(planted.getFarm());
        harvested.setCrop(planted.getCrop());
        harvested.setSeason(planted.getSeason());
        harvested.setDateHarvested(dto.getDateHarvested() != null ? dto.getDateHarvested() : LocalDate.now());

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

        harvested.setActualYield(dto.getActualAmount());
        if (dto.getDateHarvested() != null) {
            harvested.setDateHarvested(dto.getDateHarvested());
        }

        Harvested updated = harvestedRepository.save(harvested);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteHarvested(Long id) {
        if (!harvestedRepository.existsById(id)) {
            throw new ResourceNotFoundException("Harvested record not found with id: " + id);
        }
        harvestedRepository.deleteById(id);
    }

    private HarvestedCropResponseDTO mapToResponseDTO(Harvested harvested) {
        return new HarvestedCropResponseDTO(
                harvested.getId(),
                harvested.getActualYield(),
                harvested.getDateHarvested(),
                harvested.getFarm().getName(),
                harvested.getCrop().getName(),
                harvested.getSeason().getName()
        );
    }
}