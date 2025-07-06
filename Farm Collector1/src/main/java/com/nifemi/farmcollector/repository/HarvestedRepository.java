package com.nifemi.farmcollector.repository;

import com.nifemi.farmcollector.entity.Harvested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestedRepository extends JpaRepository<Harvested, Long> {

    List<Harvested> findByFarm_Id(Long farmId);

    List<Harvested> findByCrop_Id(Long cropId);

    List<Harvested> findBySeason_Id(Long seasonId);
}