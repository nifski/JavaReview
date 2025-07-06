package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.CropDetailsDTO;
import java.util.List;

public interface CropService {
    CropDetailsDTO createCrop(CropDetailsDTO cropDetailsDTO);
    CropDetailsDTO getCropById(Long id);
    List<CropDetailsDTO> getAllCrops();
    CropDetailsDTO updateCrop(Long id, CropDetailsDTO cropDetailsDTO);
    void deleteCrop(Long id);

    List<CropDetailsDTO> findAllCropsByFarmId(Long farmId);
    List<CropDetailsDTO> findCropsByFullName(String fullName);
}
