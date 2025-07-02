package com.nifemi.farmcollector.dto;

public class HarvestedCropResponseDTO {

    private Long id;
    private Long plantedCropId;  // Actual harvested amount in tons

    public HarvestedCropResponseDTO() {}

    public HarvestedCropResponseDTO(Long id, Long plantedCropId, Double actualAmount) {
        this.id = id;
        this.plantedCropId = plantedCropId;
        this.actualAmount = actualAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

