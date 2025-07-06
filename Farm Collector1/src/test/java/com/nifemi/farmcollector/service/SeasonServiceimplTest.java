package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.SeasonDTO;
import com.nifemi.farmcollector.entity.Season;
import com.nifemi.farmcollector.repository.SeasonRepository;
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
class SeasonServiceImplTest {

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private SeasonServiceImpl seasonService;

    private Season season;
    private SeasonDTO seasonDTO;

    @BeforeEach
    void setUp() {
        season = new Season();
        season.setId(1L);
        season.setName("Spring");
        season.setYear(2025);

        seasonDTO = new SeasonDTO(1L, "Spring", 2025);
    }

    @Test
    void testCreateSeason() {
        when(seasonRepository.save(any(Season.class))).thenReturn(season);

        SeasonDTO result = seasonService.createSeason(seasonDTO);

        assertNotNull(result);
        assertEquals("Spring", result.getName());
        assertEquals(2025, result.getYear());
        verify(seasonRepository, times(1)).save(any(Season.class));
    }

    @Test
    void testGetSeasonById_WhenFound() {
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));

        SeasonDTO result = seasonService.getSeasonById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetSeasonById_WhenNotFound() {
        when(seasonRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            seasonService.getSeasonById(99L);
        });

        assertEquals("Season not found with id 99", exception.getMessage());
    }

    @Test
    void testGetAllSeasons() {
        when(seasonRepository.findAll()).thenReturn(Collections.singletonList(season));

        List<SeasonDTO> results = seasonService.getAllSeasons();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Spring", results.get(0).getName());
    }

    @Test
    void testUpdateSeason_WhenFound() {
        SeasonDTO updatedDto = new SeasonDTO(1L, "Summer", 2025);
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(seasonRepository.save(any(Season.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SeasonDTO result = seasonService.updateSeason(1L, updatedDto);

        assertNotNull(result);
        assertEquals("Summer", result.getName());
        verify(seasonRepository, times(1)).save(any(Season.class));
    }

    @Test
    void testUpdateSeason_WhenNotFound() {
        SeasonDTO updatedDto = new SeasonDTO(99L, "Autumn", 2025);
        when(seasonRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            seasonService.updateSeason(99L, updatedDto);
        });
    }

    @Test
    void testDeleteSeason_WhenFound() {
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));
        doNothing().when(seasonRepository).delete(season);

        assertDoesNotThrow(() -> seasonService.deleteSeason(1L));
        verify(seasonRepository, times(1)).delete(season);
    }

    @Test
    void testDeleteSeason_WhenNotFound() {
        when(seasonRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            seasonService.deleteSeason(99L);
        });
    }
}
