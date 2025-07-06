package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.SeasonDTO;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.repository.SeasonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public SeasonDTO createSeason(SeasonDTO dto) {
        Season season = new Season();
        season.setName(dto.getName());
        season.setYear(dto.getYear());
        Season saved = seasonRepository.save(season);
        return mapToDTO(saved);
    }

    @Override
    public SeasonDTO getSeasonById(Long id) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Season not found with id " + id));
        return mapToDTO(season);
    }

    @Override
    public List<SeasonDTO> getAllSeasons() {
        return seasonRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeasonDTO updateSeason(Long id, SeasonDTO dto) {
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

    private SeasonDTO mapToDTO(Season season) {
        return new SeasonDTO(season.getId(), season.getName(), season.getYear());
    }
}