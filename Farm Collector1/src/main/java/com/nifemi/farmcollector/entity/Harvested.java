package com.nifemi.farmcollector.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Harvested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Farm farm;

    @ManyToOne
    private Crop crop;

    @ManyToOne
    private Season season;

    private LocalDate dateHarvested;

    private Double actualYield;

    public Harvested() {}

    public Harvested(Farm farm, Crop crop, Season season, LocalDate dateHarvested, Double actualYield) {
        this.farm = farm;
        this.crop = crop;
        this.season = season;
        this.dateHarvested = dateHarvested;
        this.actualYield = actualYield;
    }