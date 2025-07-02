package com.farmcollector.repository;

import com.farmcollector.entity.Crop;
import com.farmcollector.entity.Planted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Long> {

    Optional<Crop> findByName(String name);

    @Query("SELECT DISTINCT c FROM Crop c JOIN Planted p ON p.crop = c WHERE p.farm.id = :farmId")
    List<Crop> findAllCropsByFarmId(@Param("farmId") Long farmId);

    @Query("SELECT DISTINCT c FROM Crop c JOIN Planted p ON p.crop = c WHERE p.farm.id = :farmId")
    List<Crop> findAllCropsWithPlantingDatesByFarmId(@Param("farmId") Long farmId);
}
