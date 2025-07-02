package com.farmcollector.service;

import com.farmcollector.dto.FarmDetailsDTO;
import java.util.List;

public interface FarmService {
    FarmDetailsDTO createFarm(FarmDetailsDTO farmDetailsDTO);
    FarmDetailsDTO getFarmById(Long id);
    List<FarmDetailsDTO> getAllFarms();
    FarmDetailsDTO updateFarm(Long id, FarmDetailsDTO farmDetailsDTO);
    void deleteFarm(Long id);
}
