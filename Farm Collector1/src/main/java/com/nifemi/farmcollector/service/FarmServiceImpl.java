package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.FarmDetailsDTO;
import com.nifemi.farmcollector.entity.Farm;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.repository.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public FarmDetailsDTO createFarm(FarmDetailsDTO dto) {
        Farm farm = new Farm();
        farm.setName(dto.name());
        farm.setLocation(dto.location());
        Farm savedFarm = farmRepository.save(farm);
        return new FarmDetailsDTO(savedFarm.getName(), savedFarm.getLocation(), 0, List.of());
    }

    @Override
    public FarmDetailsDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with id " + id));

        List<String> cropNames = farm.getPlantings().stream()
                .map(planted -> planted.getCrop().getName())
                .distinct()
                .collect(Collectors.toList());

        int fieldCount = farm.getPlantings().size();

        return new FarmDetailsDTO(farm.getName(), farm.getLocation(), fieldCount, cropNames);
    }

    @Override
    public List<FarmDetailsDTO> getAllFarms() {
        return farmRepository.findAll().stream()
                .map(farm -> {
                    List<String> cropNames = farm.getPlantings().stream()
                            .map(planted -> planted.getCrop().getName())
                            .distinct()
                            .collect(Collectors.toList());
                    int fieldCount = farm.getPlantings().size();
                    return new FarmDetailsDTO(farm.getName(), farm.getLocation(), fieldCount, cropNames);
                })
                .collect(Collectors.toList());
    }

    @Override
    public FarmDetailsDTO updateFarm(Long id, FarmDetailsDTO dto) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with id " + id));

        farm.setName(dto.name());
        farm.setLocation(dto.location());
        Farm updatedFarm = farmRepository.save(farm);

        List<String> cropNames = updatedFarm.getPlantings().stream()
                .map(planted -> planted.getCrop().getName())
                .distinct()
                .collect(Collectors.toList());
        int fieldCount = updatedFarm.getPlantings().size();

        return new FarmDetailsDTO(updatedFarm.getName(), updatedFarm.getLocation(), fieldCount, cropNames);
    }

    @Override
    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new RuntimeException("Farm not found with id " + id);
        }
        farmRepository.deleteById(id);
    }
}