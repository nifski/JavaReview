package com.nifemi.farmcollector.dto;

import java.time.LocalDate;

public class HarvestedDetailsDTO {

    private Long id;
    private Long plantedId;
    private String farmName;
    private String cropName;
    private String season;
    private Double actualAmount;
    private LocalDate dateHarvested;

    public HarvestedDetailsDTO() {
    }

    public HarvestedDetailsDTO(Long id, Long plantedId, String farmName, String cropName,
                               String season, Double actualAmount, LocalDate dateHarvested) {
        this.id = id;
        this.plantedId = plantedId;
        this.farmName = farmName;
        this.cropName = cropName;
        this.season = season;
        this.actualAmount = actualAmount;
        this.dateHarvested = dateHarvested;
    }

    public Long getId() {
        return id;
    }

    public Long getPlantedId() {
        return plantedId;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getCropName() {
        return cropName;
    }

    public String getSeason() {
        return season;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public LocalDate getDateHarvested() {
        return dateHarvested;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlantedId(Long plantedId) {
        this.plantedId = plantedId;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public void setDateHarvested(LocalDate dateHarvested) {
        this.dateHarvested = dateHarvested;
    }
}