package com.farmcollector.service;

import com.farmcollector.dto.HarvestedDetailsDTO;
import java.util.List;

public interface HarvestedService {

    HarvestedDetailsDTO createHarvested(HarvestedDetailsDTO harvestedDetailsDTO);

    HarvestedDetailsDTO getHarvestedById(Long id);

    List<HarvestedDetailsDTO> getAllHarvests();

    HarvestedDetailsDTO updateHarvested(Long id, HarvestedDetailsDTO harvestedDetailsDTO);

    void deleteHarvested(Long id);

    List<HarvestedDetailsDTO> findByPlantedId(Long plantedId);

    List<HarvestedDetailsDTO> findByCropId(Long cropId);

}
