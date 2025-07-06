package com.nifemi.farmcollector.dto;

import java.time.LocalDate;

public class HarvestedCropRequestDTO {

    private Long plantedCropId;
    private Double actualAmount;
    private LocalDate dateHarvested;

    public HarvestedCropRequestDTO() {
    }

    public HarvestedCropRequestDTO(Long plantedCropId, Double actualAmount, LocalDate dateHarvested) {
        this.plantedCropId = plantedCropId;
        this.actualAmount = actualAmount;
        this.dateHarvested = dateHarvested;
    }

    public Long getPlantedCropId() {
        return plantedCropId;
    }

    public void setPlantedCropId(Long plantedCropId) {
        this.plantedCropId = plantedCropId;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public LocalDate getDateHarvested() {
        return dateHarvested;
    }

    public void setDateHarvested(LocalDate dateHarvested) {
        this.dateHarvested = dateHarvested;
    }
}