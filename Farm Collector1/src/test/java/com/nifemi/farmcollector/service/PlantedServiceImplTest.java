package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.PlantedDetailsDTO;
import com.nifemi.farmcollector.entity.Crop;
import com.nifemi.farmcollector.entity.Farm;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.exception.ResourceNotFoundException;
import com.nifemi.farmcollector.repository.CropRepository;
import com.nifemi.farmcollector.repository.FarmRepository;
import com.nifemi.farmcollector.repository.PlantedRepository;
import com.nifemi.farmcollector.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantedServiceImplTest {

    @Mock
    private PlantedRepository plantedRepository;
    @Mock
    private FarmRepository farmRepository;
    @Mock
    private CropRepository cropRepository;
    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private PlantedServiceImpl plantedService;

    private Planted planted;
    private PlantedDetailsDTO plantedDetailsDTO;
    private Farm farm;
    private Crop crop;
    private Season season;

    @BeforeEach
    void setUp() {
        farm = new Farm();
        farm.setId(1L);
        farm.setName("Test Farm");

        crop = new Crop();
        crop.setId(10L);
        crop.setName("Test Crop");

        season = new Season();
        season.setId(5L);
        season.setName("Spring");

        planted = new Planted();
        planted.setId(100L);
        planted.setPlantingArea(50.5);
        planted.setExpectedYield(1000.0);
        planted.setFarm(farm);
        planted.setCrop(crop);
        planted.setSeason(season);
        planted.setPlantedDate(LocalDate.now());

        plantedDetailsDTO = new PlantedDetailsDTO(
                100L, 1L, 10L, "Spring", 50.5, 1000.0, LocalDate.now()
        );
    }

    @Test
    void testCreatePlanted() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(cropRepository.findById(10L)).thenReturn(Optional.of(crop));
        when(seasonRepository.findByName("Spring")).thenReturn(Optional.of(season));
        when(plantedRepository.save(any(Planted.class))).thenReturn(planted);

        PlantedDetailsDTO result = plantedService.createPlanted(plantedDetailsDTO);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(1L, result.getFarmId());
        assertEquals(10L, result.getCropId());
        verify(plantedRepository, times(1)).save(any(Planted.class));
    }

    @Test
    void testGetPlantedById_WhenFound() {
        when(plantedRepository.findById(100L)).thenReturn(Optional.of(planted));

        PlantedDetailsDTO result = plantedService.getPlantedById(100L);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Spring", result.getSeasonName());
    }

    @Test
    void testGetPlantedById_WhenNotFound() {
        when(plantedRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> plantedService.getPlantedById(99L));
    }

    @Test
    void testGetAllPlantings() {
        when(plantedRepository.findAll()).thenReturn(Collections.singletonList(planted));

        List<PlantedDetailsDTO> results = plantedService.getAllPlantings();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(100L, results.get(0).getId());
    }

    @Test
    void testUpdatePlanted_WhenFound() {
        PlantedDetailsDTO updatedDto = new PlantedDetailsDTO();
        updatedDto.setPlantingArea(60.0);
        updatedDto.setExpectedYield(1200.0);

        when(plantedRepository.findById(100L)).thenReturn(Optional.of(planted));
        when(plantedRepository.save(any(Planted.class))).thenReturn(planted);

        PlantedDetailsDTO result = plantedService.updatePlanted(100L, updatedDto);

        assertNotNull(result);
        assertEquals(1200.0, result.getExpectedYield());
        verify(plantedRepository, times(1)).save(planted);
    }

    @Test
    void testUpdatePlanted_WhenNotFound() {
        when(plantedRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> plantedService.updatePlanted(99L, plantedDetailsDTO));
    }

    @Test
    void testDeletePlanted_WhenFound() {
        when(plantedRepository.existsById(100L)).thenReturn(true);
        doNothing().when(plantedRepository).deleteById(100L);

        assertDoesNotThrow(() -> plantedService.deletePlanted(100L));
        verify(plantedRepository, times(1)).deleteById(100L);
    }

    @Test
    void testDeletePlanted_WhenNotFound() {
        when(plantedRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> plantedService.deletePlanted(99L));
        verify(plantedRepository, never()).deleteById(anyLong());
    }

    @Test
    void testFindByCropId() {
        when(plantedRepository.findByCrop_Id(10L)).thenReturn(Collections.singletonList(planted));

        List<PlantedDetailsDTO> results = plantedService.findByCropId(10L);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(10L, results.get(0).getCropId());
    }

    @Test
    void testFindByFarmId() {
        when(plantedRepository.findByFarm_Id(1L)).thenReturn(Collections.singletonList(planted));

        List<PlantedDetailsDTO> results = plantedService.findByFarmId(1L);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1L, results.get(0).getFarmId());
    }
}