package com.farmcollector.repository;

import com.farmcollector.dto.FarmDetailsDTO;
import com.farmcollector.entity.Farm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query("""
        SELECT new com.farmcollector.dto.FarmDetailsDTO(
            f.name,
            f.location,
            SIZE(f.plantings),
            (
                SELECT DISTINCT p.crop.name
                FROM Planted p
                WHERE p.farm.id = f.id
            )
        )
        FROM Farm f
        WHERE f.id = :id
    """)
    FarmDetailsDTO getFarmDetailsById(@Param("id") Long id);
}
