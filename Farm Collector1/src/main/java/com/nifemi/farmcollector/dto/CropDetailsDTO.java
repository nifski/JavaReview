package com.nifemi.farmcollector.dto;

import java.util.List;

public class CropDetailsDTO {

    private Long id;
    private String name;
    private String description;

    private List<PlantedDetailsDTO> plantings;

    private List<HarvestedDetailsDTO> harvests;

    public CropDetailsDTO() {}

    public CropDetailsDTO(Long id, String name, String description,
                          List<PlantedDetailsDTO> plantings,
                          List<HarvestedDetailsDTO> harvests) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.plantings = plantings;
        this.harvests = harvests;
    }

}
