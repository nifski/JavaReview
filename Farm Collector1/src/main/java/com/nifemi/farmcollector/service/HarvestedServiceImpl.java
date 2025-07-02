package com.farmcollector.service.impl;

import com.farmcollector.dto.HarvestedDetailsDTO;
import com.farmcollector.entity.Harvested;
import com.farmcollector.exception.ResourceNotFoundException;
import com.farmcollector.repository.HarvestedRepository;
import com.farmcollector.service.HarvestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestedServiceImpl implements HarvestedService {

    private final HarvestedRepository harvestedRepository;

    @Autowired
    public HarvestedServiceImpl(HarvestedRepository harvestedRepository) {
        this.harvestedRepository = harvestedRepository;
    }

    @Override
    public HarvestedDetailsDTO createHarvested(HarvestedDetailsDTO dto) {
        Harvested harvested = mapToEntity(dto);
        Harvested saved = harvestedRepository.save(harvested);
        return mapToDTO(saved);
    }

    @Override
    public HarvestedDetailsDTO getHarvestedById(Long id) {
        Harvested harvested = harvestedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvested record not found with id: " + id));
        return mapToDTO(harvested);
    }

    @Override
    public List<HarvestedDetailsDTO> getAllHarvests() {
        return harvestedRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestedDetailsDTO updateHarvested(Long id, HarvestedDetailsDTO dto) {
        Harvested harvested = harvestedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvested record not found with id: " + id));

        harvested.setActualAmount(dto.getActualAmount());
        // Update other fields as needed

        Harvested updated = harvestedRepository.save(harvested);
        return mapToDTO(updated);
    }

    @Override
    public void deleteHarvested(Long id) {
        Harvested harvested = harvestedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvested record not found with id: " + id));
        harvestedRepository.delete(harvested);
    }

    @Override
    public List<HarvestedDetailsDTO> findByPlantedId(Long plantedId) {
        return harvestedRepository.findByPlantedId(plantedId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HarvestedDetailsDTO> findByCropId(Long cropId) {
        return harvestedRepository.findByCropId(cropId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Utility methods for entity-DTO mapping
    private HarvestedDetailsDTO mapToDTO(Harvested harvested) {
        HarvestedDetailsDTO dto = new HarvestedDetailsDTO();
        dto.setId(harvested.getId());
        dto.setActualAmount(harvested.getActualAmount());
        dto.setPlantedId(harvested.getPlanted().getId());
        dto.setCropId(harvested.getPlanted().getCrop().getId());
        dto.setFarmId(harvested.getPlanted().getFarm().getId());
        // Add other fields as needed
        return dto;
    }

    private Harvested mapToEntity(HarvestedDetailsDTO dto) {
        Harvested harvested = new Harvested();
        harvested.setActualAmount(dto.getActualAmount());
        // For planted relation, fetch planted entity in controller/service before calling this or adjust accordingly
        return harvested;
    }
}
