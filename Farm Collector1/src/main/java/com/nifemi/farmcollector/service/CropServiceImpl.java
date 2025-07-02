package com.farmcollector.service.impl;

import com.farmcollector.dto.CropDetailsDTO;
import com.farmcollector.entity.Crop;
import com.farmcollector.repository.CropRepository;
import com.farmcollector.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;

    @Override
    public CropDetailsDTO createCrop(CropDetailsDTO dto) {
        Crop crop = new Crop();
        crop.setName(dto.getName());
        crop.setDescription(dto.getDescription());
        Crop saved = cropRepository.save(crop);
        return mapToDTO(saved);
    }

    @Override
    public CropDetailsDTO getCropById(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with id " + id));
        return mapToDTO(crop);
    }

    @Override
    public List<CropDetailsDTO> getAllCrops() {
        return cropRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CropDetailsDTO updateCrop(Long id, CropDetailsDTO dto) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with id " + id));
        crop.setName(dto.getName());
        crop.setDescription(dto.getDescription());
        Crop updated = cropRepository.save(crop);
        return mapToDTO(updated);
    }

    @Override
    public void deleteCrop(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with id " + id));
        cropRepository.delete(crop);
    }

    @Override
    public List<CropDetailsDTO> findAllCropsByFarmId(Long farmId) {
        return cropRepository.findCropsByFarmId(farmId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CropDetailsDTO> findCropsByFullName(String fullName) {
        return cropRepository.findByName(fullName)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private CropDetailsDTO mapToDTO(Crop crop) {
        return new CropDetailsDTO(crop.getId(), crop.getName(), crop.getDescription(), null, null);
    }
}
