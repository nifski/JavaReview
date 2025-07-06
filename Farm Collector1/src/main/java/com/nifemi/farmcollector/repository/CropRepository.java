package com.nifemi.farmcollector.repository;

import com.nifemi.farmcollector.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByName(String name);

    @Query("SELECT DISTINCT c FROM Crop c JOIN Planted p ON p.crop = c WHERE p.farm.id = :farmId")
    List<Crop> findAllCropsByFarmId(@Param("farmId") Long farmId);

}