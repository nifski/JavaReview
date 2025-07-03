package com.nifemi.farmcollector.service;

import com.farmcollector.dto.SeasonReportDTO;
import com.farmcollector.dto.SeasonReportByCropDTO;
import com.farmcollector.dto.SeasonReportByFarmDTO;
import com.farmcollector.entity.Harvested;
import com.farmcollector.entity.Planted;
import com.farmcollector.entity.Season;
import com.farmcollector.repository.HarvestedRepository;
import com.farmcollector.repository.PlantedRepository;
import com.farmcollector.repository.SeasonRepository;
import com.farmcollector.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final PlantedRepository plantedRepository;
    private final HarvestedRepository harvestedRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    public ReportServiceImpl(PlantedRepository plantedRepository,
                             HarvestedRepository harvestedRepository,
                             SeasonRepository seasonRepository) {
        this.plantedRepository = plantedRepository;
        this.harvestedRepository = harvestedRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public SeasonReportDTO generateSeasonReport(String seasonName) {
        Season season = seasonRepository.findByFullName(seasonName)
                .orElseThrow(() -> new ResourceNotFoundException("Season not found: " + seasonName));

        List<Planted> plantings = plantedRepository.findBySeasonId(season.getId());
        List<Harvested> harvests = harvestedRepository.findByPlanted_Season_Id(season.getId());

        Map<String, List<Planted>> plantedByFarm = plantings.stream()
                .collect(Collectors.groupingBy(p -> p.getFarm().getName()));

        List<SeasonReportByFarmDTO> farmReports = new ArrayList<>();
        for (Map.Entry<String, List<Planted>> entry : plantedByFarm.entrySet()) {
            String farmName = entry.getKey();
            for (Planted p : entry.getValue()) {
                Optional<Harvested> harvest = harvests.stream()
                        .filter(h -> h.getPlanted().getId().equals(p.getId()))
                        .findFirst();
                double actual = harvest.map(Harvested::getActualAmount).orElse(0.0);
                farmReports.add(new SeasonReportByFarmDTO(farmName, p.getCrop().getName(), p.getAmountExpected(), actual));
            }
        }

        Map<String, List<Planted>> plantedByCrop = plantings.stream()
                .collect(Collectors.groupingBy(p -> p.getCrop().getName()));

        List<SeasonReportByCropDTO> cropReports = new ArrayList<>();
        for (Map.Entry<String, List<Planted>> entry : plantedByCrop.entrySet()) {
            String cropName = entry.getKey();
            double totalExpected = entry.getValue().stream().mapToDouble(Planted::getAmountExpected).sum();
            double totalActual = harvests.stream()
                    .filter(h -> h.getPlanted().getCrop().getName().equals(cropName))
                    .mapToDouble(Harvested::getActualAmount)
                    .sum();
            cropReports.add(new SeasonReportByCropDTO(cropName, totalExpected, totalActual));
        }

        return new SeasonReportDTO(season.getFullName(), farmReports, cropReports);
    }
}
