package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.CropDetailsDTO;
import com.nifemi.farmcollector.entity.Crop;
import com.nifemi.farmcollector.repository.CropRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CropServiceImplTest {

    @Mock
    private CropRepository cropRepository;

    @InjectMocks
    private CropServiceImpl cropService;

    private Crop crop;
    private CropDetailsDTO cropDetailsDTO;

    @BeforeEach
    void setUp() {
        crop = new Crop();
        crop.setId(1L);
        crop.setName("Corn");
        crop.setDescription("A tall annual grass that yields kernels set in rows on a cob.");

        cropDetailsDTO = new CropDetailsDTO(1L, "Corn", "A tall annual grass that yields kernels set in rows on a cob.", null, null);
    }

    @Test
    void testCreateCrop() {
        when(cropRepository.save(any(Crop.class))).thenReturn(crop);
        CropDetailsDTO result = cropService.createCrop(cropDetailsDTO);
        assertNotNull(result);
        assertEquals("Corn", result.getName());
        verify(cropRepository, times(1)).save(any(Crop.class));
    }

    @Test
    void testGetCropById_WhenCropExists() {
        when(cropRepository.findById(1L)).thenReturn(Optional.of(crop));
        CropDetailsDTO result = cropService.getCropById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Corn", result.getName());
    }

    @Test
    void testGetCropById_WhenCropDoesNotExist() {
        when(cropRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> cropService.getCropById(99L));
        assertEquals("Crop not found with id 99", exception.getMessage());
    }

    @Test
    void testGetAllCrops() {
        when(cropRepository.findAll()).thenReturn(Collections.singletonList(crop));
        List<CropDetailsDTO> results = cropService.getAllCrops();
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Corn", results.get(0).getName());
    }

    @Test
    void testUpdateCrop_WhenCropExists() {
        CropDetailsDTO updatedDto = new CropDetailsDTO(1L, "Sweetcorn", "A variety of maize with a high sugar content.", null, null);
        when(cropRepository.findById(1L)).thenReturn(Optional.of(crop));
        when(cropRepository.save(any(Crop.class))).thenAnswer(invocation -> invocation.getArgument(0));
        CropDetailsDTO result = cropService.updateCrop(1L, updatedDto);
        assertNotNull(result);
        assertEquals("Sweetcorn", result.getName());
        assertEquals("A variety of maize with a high sugar content.", result.getDescription());
        verify(cropRepository, times(1)).save(any(Crop.class));
    }

    @Test
    void testUpdateCrop_WhenCropDoesNotExist() {
        CropDetailsDTO updatedDto = new CropDetailsDTO(99L, "Non-existent Crop", "N/A", null, null);
        when(cropRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> cropService.updateCrop(99L, updatedDto));
        assertEquals("Crop not found with id 99", exception.getMessage());
        verify(cropRepository, never()).save(any(Crop.class));
    }

    @Test
    void testDeleteCrop_WhenCropExists() {
        when(cropRepository.findById(1L)).thenReturn(Optional.of(crop));
        doNothing().when(cropRepository).delete(crop);
        assertDoesNotThrow(() -> cropService.deleteCrop(1L));
        verify(cropRepository, times(1)).delete(crop);
    }

    @Test
    void testDeleteCrop_WhenCropDoesNotExist() {
        when(cropRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> cropService.deleteCrop(99L));
        assertEquals("Crop not found with id 99", exception.getMessage());
        verify(cropRepository, never()).delete(any(Crop.class));
    }

    @Test
    void testFindAllCropsByFarmId() {
        // Corrected the method name in the mock setup
        when(cropRepository.findAllCropsByFarmId(10L)).thenReturn(Collections.singletonList(crop));
        List<CropDetailsDTO> results = cropService.findAllCropsByFarmId(10L);
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Corn", results.get(0).getName());
    }

    @Test
    void testFindCropsByFullName() {
        when(cropRepository.findByName("Corn")).thenReturn(Collections.singletonList(crop));
        List<CropDetailsDTO> results = cropService.findCropsByFullName("Corn");
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Corn", results.get(0).getName());
    }
}