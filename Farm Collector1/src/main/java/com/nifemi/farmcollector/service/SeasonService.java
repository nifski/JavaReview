package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.SeasonDTO;
import java.util.List;

public interface SeasonService {
    SeasonDTO createSeason(SeasonDTO seasonDTO);
    SeasonDTO getSeasonById(Long id);
    List<SeasonDTO> getAllSeasons();
    SeasonDTO updateSeason(Long id, SeasonDTO seasonDTO);
    void deleteSeason(Long id);
}