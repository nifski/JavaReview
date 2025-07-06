package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.HarvestedCropRequestDTO;
import com.nifemi.farmcollector.dto.HarvestedCropResponseDTO;
import com.nifemi.farmcollector.entity.Crop;
import com.nifemi.farmcollector.entity.Farm;
import com.nifemi.farmcollector.entity.Harvested;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.exception.ResourceNotFoundException;
import com.nifemi.farmcollector.repository.HarvestedRepository;
import com.nifemi.farmcollector.repository.PlantedRepository;
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
class HarvestedServiceImplTest {

    @Mock
    private HarvestedRepository harvestedRepository;

    @Mock
    private PlantedRepository plantedRepository;

    @InjectMocks
    private HarvestedServiceImpl harvestedService;

    private Planted planted;
    private Harvested harvested;
    private HarvestedCropRequestDTO requestDTO;
    private Farm farm;
    private Crop crop;
    private Season season;

    @BeforeEach
    void setUp() {
        farm = new Farm();
        farm.setName("Test Farm");
        crop = new Crop();
        crop.setName("Test Crop");
        season = new Season();
        season.setName("Test Season");

        planted = new Planted();
        planted.setId(1L);
        planted.setFarm(farm);
        planted.setCrop(crop);
        planted.setSeason(season);

        harvested = new Harvested();
        harvested.setId(100L);
        harvested.setActualYield(500.0);
        harvested.setDateHarvested(LocalDate.now());
        harvested.setFarm(farm);
        harvested.setCrop(crop);
        harvested.setSeason(season);

        requestDTO = new HarvestedCropRequestDTO();
        requestDTO.setPlantedCropId(1L);
        requestDTO.setActualAmount(500.0);
        requestDTO.setDateHarvested(LocalDate.now());
    }

    @Test
    void testCreateHarvested_Success() {
        when(plantedRepository.findById(1L)).thenReturn(Optional.of(planted));
        when(harvestedRepository.save(any(Harvested.class))).thenReturn(harvested);

        HarvestedCropResponseDTO result = harvestedService.createHarvested(requestDTO);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Test Farm", result.getFarmName());
        assertEquals("Test Crop", result.getCropName());
        verify(harvestedRepository, times(1)).save(any(Harvested.class));
    }

    @Test
    void testCreateHarvested_PlantedNotFound() {
        when(plantedRepository.findById(99L)).thenReturn(Optional.empty());
        requestDTO.setPlantedCropId(99L);

        assertThrows(ResourceNotFoundException.class, () -> harvestedService.createHarvested(requestDTO));
    }

    @Test
    void testGetHarvestedById_WhenFound() {
        when(harvestedRepository.findById(100L)).thenReturn(Optional.of(harvested));

        HarvestedCropResponseDTO result = harvestedService.getHarvestedById(100L);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Test Farm", result.getFarmName());
    }

    @Test
    void testGetHarvestedById_WhenNotFound() {
        when(harvestedRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> harvestedService.getHarvestedById(99L));
    }

    @Test
    void testGetAllHarvests() {
        when(harvestedRepository.findAll()).thenReturn(Collections.singletonList(harvested));

        List<HarvestedCropResponseDTO> results = harvestedService.getAllHarvests();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(100L, results.get(0).getId());
    }

    @Test
    void testUpdateHarvested_Success() {
        requestDTO.setActualAmount(600.0);
        when(harvestedRepository.findById(100L)).thenReturn(Optional.of(harvested));
        when(harvestedRepository.save(any(Harvested.class))).thenReturn(harvested);

        HarvestedCropResponseDTO result = harvestedService.updateHarvested(100L, requestDTO);

        assertNotNull(result);
        assertEquals(600.0, result.getActualAmount());
        verify(harvestedRepository, times(1)).save(any(Harvested.class));
    }

    @Test
    void testUpdateHarvested_WhenHarvestedNotFound() {
        when(harvestedRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> harvestedService.updateHarvested(99L, requestDTO));
    }

    @Test
    void testDeleteHarvested_Success() {
        when(harvestedRepository.existsById(100L)).thenReturn(true);
        doNothing().when(harvestedRepository).deleteById(100L);

        assertDoesNotThrow(() -> harvestedService.deleteHarvested(100L));
        verify(harvestedRepository, times(1)).deleteById(100L);
    }

    @Test
    void testDeleteHarvested_WhenNotFound() {
        when(harvestedRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> harvestedService.deleteHarvested(99L));
        verify(harvestedRepository, never()).deleteById(anyLong());
    }
}