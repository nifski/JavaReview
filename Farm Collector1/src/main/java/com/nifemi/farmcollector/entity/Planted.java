package com.nifemi.farmcollector.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Planted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate plantedDate;

    private double expectedYield;

    private double actualYield;

    private Double plantingArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    public Planted() {
    }

    public Planted(LocalDate plantedDate, double expectedYield, double actualYield, Double plantingArea, Farm farm, Crop crop, Season season) {
        this.plantedDate = plantedDate;
        this.expectedYield = expectedYield;
        this.actualYield = actualYield;
        this.plantingArea = plantingArea;
        this.farm = farm;
        this.crop = crop;
        this.season = season;
    }

    public Long getId() { return id; }
    public LocalDate getPlantedDate() { return plantedDate; }
    public double getExpectedYield() { return expectedYield; }
    public double getActualYield() { return actualYield; }
    public Double getPlantingArea() { return plantingArea; }
    public Farm getFarm() { return farm; }
    public Crop getCrop() { return crop; }
    public Season getSeason() { return season; }

    public void setId(Long id) { this.id = id; }
    public void setPlantedDate(LocalDate plantedDate) { this.plantedDate = plantedDate; }
    public void setExpectedYield(double expectedYield) { this.expectedYield = expectedYield; }
    public void setActualYield(double actualYield) { this.actualYield = actualYield; }
    public void setPlantingArea(Double plantingArea) { this.plantingArea = plantingArea; }
    public void setFarm(Farm farm) { this.farm = farm; }
    public void setCrop(Crop crop) { this.crop = crop; }
    public void setSeason(Season season) { this.season = season; }

    @Override
    public String toString() {
        return "Planted{" +
                "id=" + id +
                ", plantedDate=" + plantedDate +
                ", expectedYield=" + expectedYield +
                ", actualYield=" + actualYield +
                ", plantingArea=" + plantingArea +
                ", farm_id=" + (farm != null ? farm.getId() : "null") +
                ", crop_id=" + (crop != null ? crop.getId() : "null") +
                ", season_id=" + (season != null ? season.getId() : "null") +
                '}';
    }
}