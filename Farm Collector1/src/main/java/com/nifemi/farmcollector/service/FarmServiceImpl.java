package com.farmcollector.service.impl;

import com.farmcollector.dto.FarmDetailsDTO;
import com.farmcollector.entity.Farm;
import com.farmcollector.repository.FarmRepository;
import com.farmcollector.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Override
    public FarmDetailsDTO createFarm(FarmDetailsDTO dto) {
        Farm farm = new Farm();
        farm.setName(dto.getName());
        farm.setLocation(dto.getLocation());
        farm.setNumberOfFields(dto.getNumberOfFields());
        Farm saved = farmRepository.save(farm);
        return mapToDTO(saved);
    }

    @Override
    public FarmDetailsDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with id " + id));
        return mapToDTO(farm);
    }

    @Override
    public List<FarmDetailsDTO> getAllFarms() {
        return farmRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FarmDetailsDTO updateFarm(Long id, FarmDetailsDTO dto) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with id " + id));
        farm.setName(dto.getName());
        farm.setLocation(dto.getLocation());
        farm.setNumberOfFields(dto.getNumberOfFields());
        Farm updated = farmRepository.save(farm);
        return mapToDTO(updated);
    }

    @Override
    public void deleteFarm(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with id " + id));
        farmRepository.delete(farm);
    }

    private FarmDetailsDTO mapToDTO(Farm farm) {
        return new FarmDetailsDTO(farm.getId(), farm.getName(), farm.getLocation(), farm.getNumberOfFields());
    }
}
