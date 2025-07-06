package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.HarvestedCropRequestDTO;
import com.nifemi.farmcollector.dto.HarvestedCropResponseDTO;
import java.util.List;

public interface HarvestedService {

    HarvestedCropResponseDTO createHarvested(HarvestedCropRequestDTO harvestedCropRequestDTO);

    HarvestedCropResponseDTO getHarvestedById(Long id);

    List<HarvestedCropResponseDTO> getAllHarvests();

    HarvestedCropResponseDTO updateHarvested(Long id, HarvestedCropRequestDTO harvestedCropRequestDTO);

    void deleteHarvested(Long id);
}