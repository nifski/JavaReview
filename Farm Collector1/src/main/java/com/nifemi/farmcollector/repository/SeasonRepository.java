package com.nifemi.farmcollector.repository;

import com.nifemi.farmcollector.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    Optional<Season> findByName(String name);

}