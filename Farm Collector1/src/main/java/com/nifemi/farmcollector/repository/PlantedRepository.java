package com.farmcollector.repository;

import com.farmcollector.entity.Planted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlantedRepository extends JpaRepository<Planted, Long> {

    List<Planted> findByCropId(Long cropId);

    List<Planted> findByFarmId(Long farmId);

    List<Planted> findByCropIdAndFarmId(Long cropId, Long farmId);

    List<Planted> findByCropIdAndSeason(Long cropId, String season);

    @Query("SELECT DISTINCT p.plantingDate FROM Planted p WHERE p.farm.id = :farmId ORDER BY p.plantingDate")
    List<java.time.LocalDate> findDistinctPlantingDatesByFarmId(@Param("farmId") Long farmId);
}
