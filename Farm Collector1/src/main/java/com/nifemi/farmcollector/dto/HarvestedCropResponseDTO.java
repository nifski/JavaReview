package com.nifemi.farmcollector.dto;

import java.time.LocalDate;

public class HarvestedCropResponseDTO {

    private Long id;
    private Double actualAmount;
    private LocalDate dateHarvested;
    private String farmName;
    private String cropName;
    private String seasonName;

    public HarvestedCropResponseDTO() {
    }

    public HarvestedCropResponseDTO(Long id, Double actualAmount, LocalDate dateHarvested, String farmName, String cropName, String seasonName) {
        this.id = id;
        this.actualAmount = actualAmount;
        this.dateHarvested = dateHarvested;
        this.farmName = farmName;
        this.cropName = cropName;
        this.seasonName = seasonName;
    }

    public Long getId() {
        return id;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public LocalDate getDateHarvested() {
        return dateHarvested;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getCropName() {
        return cropName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public void setDateHarvested(LocalDate dateHarvested) {
        this.dateHarvested = dateHarvested;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
}