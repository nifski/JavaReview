package com.nifemi.farmcollector.dto;

import java.time.LocalDate;

public class PlantedDetailsDTO {

    private Long id;
    private Long farmId;
    private Long cropId;
    private String seasonName;
    private double plantingArea;
    private double expectedYield;
    private LocalDate plantedDate;

    public PlantedDetailsDTO() {
    }

    public PlantedDetailsDTO(Long id, Long farmId, Long cropId, String seasonName, double plantingArea, double expectedYield, LocalDate plantedDate) {
        this.id = id;
        this.farmId = farmId;
        this.cropId = cropId;
        this.seasonName = seasonName;
        this.plantingArea = plantingArea;
        this.expectedYield = expectedYield;
        this.plantedDate = plantedDate;
    }

    public Long getId() {
        return id;
    }

    public Long getFarmId() {
        return farmId;
    }

    public Long getCropId() {
        return cropId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public double getPlantingArea() {
        return plantingArea;
    }

    public double getExpectedYield() {
        return expectedYield;
    }

    public LocalDate getPlantedDate() {
        return plantedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public void setPlantingArea(double plantingArea) {
        this.plantingArea = plantingArea;
    }

    public void setExpectedYield(double expectedYield) {
        this.expectedYield = expectedYield;
    }

    public void setPlantedDate(LocalDate plantedDate) {
        this.plantedDate = plantedDate;
    }
}