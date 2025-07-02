package com.nifemi.farmcollector.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate plantedDate;

    private double expectedYield;

    private double actualYield;

    private Double plantingArea;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
}
