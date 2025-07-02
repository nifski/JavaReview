package com.farmcollector.service.impl;

import com.farmcollector.dto.SeasonDetailsDTO;
import com.farmcollector.entity.Season;
import com.farmcollector.repository.SeasonRepository;
import com.farmcollector.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public SeasonDetailsDTO createSeason(SeasonDetailsDTO dto) {
        Season season = new Season();
        season.setName(dto.getName());
        season.setYear(dto.getYear());
        Season saved = seasonRepository.save(season);
        return mapToDTO(saved);
    }

    @Override
    public SeasonDetailsDTO getSeasonById(Long id) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Season not found with id " + id));
        return mapToDTO(season);
    }

    @Override
    public List<SeasonDetailsDTO> getAllSeasons() {
        return seasonRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeasonDetailsDTO updateSeason(Long id, SeasonDetailsDTO dto) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Season not found with id " + id));
        season.setName(dto.getName());
        season.setYear(dto.getYear());
        Season updated = seasonRepository.save(season);
        return mapToDTO(updated);
    }

    @Override
    public void deleteSeason(Long id) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Season not found with id " + id));
        seasonRepository.delete(season);
    }

    private SeasonDetailsDTO mapToDTO(Season season) {
        return new SeasonDetailsDTO(season.getId(), season.getName(), season.getYear());
    }
}
