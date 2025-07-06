package com.nifemi.farmcollector.dto;

import java.util.List;

public class CropDetailsDTO {

    private Long id;
    private String name;
    private String description;
    private List<PlantedDetailsDTO> plantings;
    private List<HarvestedDetailsDTO> harvests;

    public CropDetailsDTO() {
    }

    public CropDetailsDTO(Long id, String name, String description,
                          List<PlantedDetailsDTO> plantings,
                          List<HarvestedDetailsDTO> harvests) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.plantings = plantings;
        this.harvests = harvests;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<PlantedDetailsDTO> getPlantings() {
        return plantings;
    }

    public List<HarvestedDetailsDTO> getHarvests() {
        return harvests;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlantings(List<PlantedDetailsDTO> plantings) {
        this.plantings = plantings;
    }

    public void setHarvests(List<HarvestedDetailsDTO> harvests) {
        this.harvests = harvests;
    }
}
