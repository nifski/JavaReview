package com.nifemi.farmcollector.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Harvested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    private LocalDate dateHarvested;

    private Double actualYield;

    public Harvested() {
    }

    public Harvested(Farm farm, Crop crop, Season season, LocalDate dateHarvested, Double actualYield) {
        this.farm = farm;
        this.crop = crop;
        this.season = season;
        this.dateHarvested = dateHarvested;
        this.actualYield = actualYield;
    }

    public Long getId() { return id; }
    public Farm getFarm() { return farm; }
    public Crop getCrop() { return crop; }
    public Season getSeason() { return season; }
    public LocalDate getDateHarvested() { return dateHarvested; }
    public Double getActualYield() { return actualYield; }

    public void setId(Long id) { this.id = id; }
    public void setFarm(Farm farm) { this.farm = farm; }
    public void setCrop(Crop crop) { this.crop = crop; }
    public void setSeason(Season season) { this.season = season; }
    public void setDateHarvested(LocalDate dateHarvested) { this.dateHarvested = dateHarvested; }
    public void setActualYield(Double actualYield) { this.actualYield = actualYield; }

    @Override
    public String toString() {
        return "Harvested{" +
                "id=" + id +
                ", farm_id=" + (farm != null ? farm.getId() : "null") +
                ", crop_id=" + (crop != null ? crop.getId() : "null") +
                ", season_id=" + (season != null ? season.getId() : "null") +
                ", dateHarvested=" + dateHarvested +
                ", actualYield=" + actualYield +
                '}';
    }
}