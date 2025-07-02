package com.nifemi.farmcollector.service;

import com.farmcollector.dto.SeasonDetailsDTO;
import java.util.List;

public interface SeasonService {
    SeasonDetailsDTO createSeason(SeasonDetailsDTO seasonDetailsDTO);
    SeasonDetailsDTO getSeasonById(Long id);
    List<SeasonDetailsDTO> getAllSeasons();
    SeasonDetailsDTO updateSeason(Long id, SeasonDetailsDTO seasonDetailsDTO);
    void deleteSeason(Long id);
}
