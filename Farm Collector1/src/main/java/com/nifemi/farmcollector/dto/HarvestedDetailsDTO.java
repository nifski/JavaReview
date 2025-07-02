package com.farmcollector.dto;

import java.time.LocalDate;

public class HarvestedDetailsDTO {

    private Long id;
    private Long plantedId;
    private String farmName;
    private String cropName;
    private String season;
    private Double actualAmount;
    private LocalDate dateHarvested;

    public HarvestedDetailsDTO() {}

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


}
