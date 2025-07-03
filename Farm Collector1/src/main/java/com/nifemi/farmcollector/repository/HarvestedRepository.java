package com.nifemi.farmcollector.repository;

import com.farmcollector.entity.Harvested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestedRepository extends JpaRepository<Harvested, Long> {

    List<Harvested> findByPlantedId(Long plantedId);

    List<Harvested> findByFarmId(Long farmId);

    List<Harvested> findByCropId(Long cropId);

    List<Harvested> findBySeason(String season);

    List<Harvested> findByFarmIdAndSeason(Long farmId, String season);

    List<Harvested> findByPlanted_Season_Id(Long seasonId);
}
