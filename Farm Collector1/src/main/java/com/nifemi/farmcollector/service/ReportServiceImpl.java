package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.SeasonCropReportDTO;
import com.nifemi.farmcollector.dto.SeasonFarmReportDTO;
import com.nifemi.farmcollector.dto.SeasonReportDTO;
import com.nifemi.farmcollector.entity.Harvested;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.exception.ResourceNotFoundException;
import com.nifemi.farmcollector.repository.HarvestedRepository;
import com.nifemi.farmcollector.repository.PlantedRepository;
import com.nifemi.farmcollector.repository.SeasonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final PlantedRepository plantedRepository;
    private final HarvestedRepository harvestedRepository;
    private final SeasonRepository seasonRepository;

    public ReportServiceImpl(PlantedRepository plantedRepository,
                             HarvestedRepository harvestedRepository,
                             SeasonRepository seasonRepository) {
        this.plantedRepository = plantedRepository;
        this.harvestedRepository = harvestedRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public SeasonReportDTO generateSeasonReport(String seasonName) {
        Season season = seasonRepository.findByName(seasonName)
                .orElseThrow(() -> new ResourceNotFoundException("Season not found: " + seasonName));

        List<Planted> plantings = plantedRepository.findBySeason_Id(season.getId());
        List<Harvested> harvests = harvestedRepository.findBySeason_Id(season.getId());

        Map<String, List<Planted>> plantedByFarm = plantings.stream()
                .collect(Collectors.groupingBy(p -> p.getFarm().getName()));

        List<SeasonFarmReportDTO> farmReports = new ArrayList<>();
        for (Map.Entry<String, List<Planted>> entry : plantedByFarm.entrySet()) {
            String farmName = entry.getKey();
            for (Planted p : entry.getValue()) {
                Optional<Harvested> harvest = harvests.stream()
                        .filter(h -> h.getFarm().getName().equals(farmName) && h.getCrop().getName().equals(p.getCrop().getName()))
                        .findFirst();
                double actual = harvest.map(Harvested::getActualYield).orElse(0.0);
                farmReports.add(new SeasonFarmReportDTO(farmName, p.getCrop().getName(), p.getExpectedYield(), actual));
            }
        }

        Map<String, List<Planted>> plantedByCrop = plantings.stream()
                .collect(Collectors.groupingBy(p -> p.getCrop().getName()));

        List<SeasonCropReportDTO> cropReports = new ArrayList<>();
        for (Map.Entry<String, List<Planted>> entry : plantedByCrop.entrySet()) {
            String cropName = entry.getKey();
            double totalExpected = entry.getValue().stream().mapToDouble(Planted::getExpectedYield).sum();
            double totalActual = harvests.stream()
                    .filter(h -> h.getCrop().getName().equals(cropName))
                    .mapToDouble(Harvested::getActualYield)
                    .sum();
            cropReports.add(new SeasonCropReportDTO(cropName, totalExpected, totalActual));
        }

        return new SeasonReportDTO(season.getName(), farmReports, cropReports);
    }
}