package com.nifemi.farmcollector.repository;

import com.nifemi.farmcollector.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    // No custom methods are needed here for now.
    // JpaRepository already provides findById(), findAll(), save(), etc.
}