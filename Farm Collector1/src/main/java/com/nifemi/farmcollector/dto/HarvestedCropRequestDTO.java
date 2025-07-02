package com.nifemi.farmcollector.dto;

public class HarvestedCropRequestDTO {

    private Long plantedCropId;
    private Double actualAmount;

    public HarvestedCropRequestDTO() {}

    public HarvestedCropRequestDTO(Long plantedCropId, Double actualAmount) {
        this.plantedCropId = plantedCropId;
        this.actualAmount = actualAmount;
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
}

