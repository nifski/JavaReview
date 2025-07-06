package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.SeasonCropReportDTO;
import com.nifemi.farmcollector.dto.SeasonFarmReportDTO;
import com.nifemi.farmcollector.dto.SeasonReportDTO;
import com.nifemi.farmcollector.entity.Crop;
import com.nifemi.farmcollector.entity.Farm;
import com.nifemi.farmcollector.entity.Harvested;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.exception.ResourceNotFoundException;
import com.nifemi.farmcollector.repository.HarvestedRepository;
import com.nifemi.farmcollector.repository.PlantedRepository;
import com.nifemi.farmcollector.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private PlantedRepository plantedRepository;

    @Mock
    private HarvestedRepository harvestedRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private Season mockSeason;
    private List<Planted> mockPlantedList;
    private List<Harvested> mockHarvestedList;

    @BeforeEach
    void setUp() {
        mockSeason = new Season();
        mockSeason.setId(1L);
        mockSeason.setName("Spring");
        mockSeason.setYear(2025);

        Farm farmA = new Farm();
        farmA.setName("Farm A");
        Farm farmB = new Farm();
        farmB.setName("Farm B");

        Crop cropCorn = new Crop();
        cropCorn.setName("Corn");
        Crop cropWheat = new Crop();
        cropWheat.setName("Wheat");

        Planted p1 = new Planted();
        p1.setFarm(farmA);
        p1.setCrop(cropCorn);
        p1.setSeason(mockSeason);
        p1.setExpectedYield(100.0);

        Planted p2 = new Planted();
        p2.setFarm(farmA);
        p2.setCrop(cropWheat);
        p2.setSeason(mockSeason);
        p2.setExpectedYield(200.0);

        Planted p3 = new Planted();
        p3.setFarm(farmB);
        p3.setCrop(cropCorn);
        p3.setSeason(mockSeason);
        p3.setExpectedYield(150.0);

        mockPlantedList = Arrays.asList(p1, p2, p3);

        Harvested h1 = new Harvested();
        h1.setCrop(cropCorn);
        h1.setFarm(farmA);
        h1.setActualYield(95.0);

        Harvested h2 = new Harvested();
        h2.setCrop(cropWheat);
        h2.setFarm(farmA);
        h2.setActualYield(210.0);

        mockHarvestedList = Arrays.asList(h1, h2);
    }

    @Test
    void generateSeasonReport_Success() {
        String seasonName = "Spring";
        when(seasonRepository.findByName(seasonName)).thenReturn(Optional.of(mockSeason));
        when(plantedRepository.findBySeason_Id(mockSeason.getId())).thenReturn(mockPlantedList);
        when(harvestedRepository.findBySeason_Id(mockSeason.getId())).thenReturn(mockHarvestedList);

        SeasonReportDTO report = reportService.generateSeasonReport(seasonName);

        assertNotNull(report);
        assertEquals(seasonName, report.getSeasonName());

        List<SeasonFarmReportDTO> farmReports = report.getByFarm();
        assertEquals(3, farmReports.size());

        SeasonFarmReportDTO farmAReportCorn = farmReports.stream()
                .filter(r -> r.getFarmName().equals("Farm A") && r.getCropType().equals("Corn"))
                .findFirst().orElse(null);
        assertNotNull(farmAReportCorn);
        assertEquals(100.0, farmAReportCorn.getExpectedAmount());
        assertEquals(95.0, farmAReportCorn.getActualAmount());

        SeasonFarmReportDTO farmBReportCorn = farmReports.stream()
                .filter(r -> r.getFarmName().equals("Farm B") && r.getCropType().equals("Corn"))
                .findFirst().orElse(null);
        assertNotNull(farmBReportCorn);
        assertEquals(150.0, farmBReportCorn.getExpectedAmount());
        assertEquals(0.0, farmBReportCorn.getActualAmount());

        List<SeasonCropReportDTO> cropReports = report.getByCrop();
        assertEquals(2, cropReports.size());

        SeasonCropReportDTO cornReport = cropReports.stream().filter(r -> r.getCropType().equals("Corn")).findFirst().orElse(null);
        assertNotNull(cornReport);
        assertEquals(250.0, cornReport.getTotalExpected());
        assertEquals(95.0, cornReport.getTotalActual());

        SeasonCropReportDTO wheatReport = cropReports.stream().filter(r -> r.getCropType().equals("Wheat")).findFirst().orElse(null);
        assertNotNull(wheatReport);
        assertEquals(200.0, wheatReport.getTotalExpected());
        assertEquals(210.0, wheatReport.getTotalActual());
    }

    @Test
    void generateSeasonReport_WhenSeasonNotFound() {
        String seasonName = "Non-existent Season";
        when(seasonRepository.findByName(seasonName)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            reportService.generateSeasonReport(seasonName);
        });

        assertEquals("Season not found: " + seasonName, exception.getMessage());
    }
}