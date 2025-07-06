package com.nifemi.farmcollector.repository;

import com.nifemi.farmcollector.entity.Planted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlantedRepository extends JpaRepository<Planted, Long> {

    List<Planted> findByCrop_Id(Long cropId);

    List<Planted> findByFarm_Id(Long farmId);

    List<Planted> findBySeason_Id(Long seasonId);

    List<Planted> findByCrop_IdAndFarm_Id(Long cropId, Long farmId);

    @Query("SELECT DISTINCT p.plantedDate FROM Planted p WHERE p.farm.id = :farmId ORDER BY p.plantedDate")
    List<LocalDate> findDistinctPlantingDatesByFarmId(@Param("farmId") Long farmId);
}