package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.FarmDetailsDTO;
import com.nifemi.farmcollector.entity.Crop;
import com.nifemi.farmcollector.entity.Farm;
import com.nifemi.farmcollector.entity.Planted;
import com.nifemi.farmcollector.exception.ResourceNotFoundException;
import com.nifemi.farmcollector.repository.FarmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FarmServiceImplTest {

    @Mock
    private FarmRepository farmRepository;

    @InjectMocks
    private FarmServiceImpl farmService;

    private Farm farm;
    private FarmDetailsDTO farmDetailsDTO;

    @BeforeEach
    void setUp() {
        Crop corn = new Crop();
        corn.setName("Corn");
        Crop wheat = new Crop();
        wheat.setName("Wheat");

        Planted planted1 = new Planted();
        planted1.setCrop(corn);
        Planted planted2 = new Planted();
        planted2.setCrop(wheat);

        farm = new Farm();
        farm.setId(1L);
        farm.setName("Green Acres");
        farm.setLocation("Springfield");
        farm.setPlantings(Arrays.asList(planted1, planted2));

        farmDetailsDTO = new FarmDetailsDTO("Green Acres", "Springfield", 2, List.of("Corn", "Wheat"));
    }

    @Test
    void testCreateFarm() {
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);

        FarmDetailsDTO result = farmService.createFarm(farmDetailsDTO);

        assertNotNull(result);
        assertEquals("Green Acres", result.name());
        verify(farmRepository, times(1)).save(any(Farm.class));
    }

    @Test
    void testGetFarmById_WhenFarmExists() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        FarmDetailsDTO result = farmService.getFarmById(1L);

        assertNotNull(result);
        assertEquals("Green Acres", result.name());
        assertEquals(2, result.fieldCount());
        assertTrue(result.crops().containsAll(List.of("Corn", "Wheat")));
    }

    @Test
    void testGetFarmById_WhenFarmDoesNotExist() {
        when(farmRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> farmService.getFarmById(99L));
    }

    @Test
    void testGetAllFarms() {
        when(farmRepository.findAll()).thenReturn(Collections.singletonList(farm));

        List<FarmDetailsDTO> results = farmService.getAllFarms();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Green Acres", results.get(0).name());
        assertEquals(2, results.get(0).fieldCount());
    }

    @Test
    void testUpdateFarm_WhenFarmExists() {
        FarmDetailsDTO updatedDto = new FarmDetailsDTO("New Green Acres", "Shelbyville", 0, null);
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);

        FarmDetailsDTO result = farmService.updateFarm(1L, updatedDto);

        assertNotNull(result);
        assertEquals("New Green Acres", result.name());
        assertEquals("Shelbyville", result.location());
        assertEquals(2, result.fieldCount());
    }

    @Test
    void testUpdateFarm_WhenFarmDoesNotExist() {
        when(farmRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> farmService.updateFarm(99L, farmDetailsDTO));
    }

    @Test
    void testDeleteFarm_WhenFarmExists() {
        when(farmRepository.existsById(1L)).thenReturn(true);
        doNothing().when(farmRepository).deleteById(1L);

        assertDoesNotThrow(() -> farmService.deleteFarm(1L));
        verify(farmRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteFarm_WhenFarmDoesNotExist() {
        when(farmRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> farmService.deleteFarm(99L));
        verify(farmRepository, never()).deleteById(anyLong());
    }
}