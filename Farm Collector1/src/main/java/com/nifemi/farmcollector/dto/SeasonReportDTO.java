package com.nifemi.farmcollector.dto;

import java.util.List;

public class SeasonReportDTO {

    private String seasonName;
    private List<SeasonFarmReportDTO> byFarm;
    private List<SeasonCropReportDTO> byCrop;

    public SeasonReportDTO() {}

    public SeasonReportDTO(String seasonName, List<SeasonFarmReportDTO> byFarm, List<SeasonCropReportDTO> byCrop) {
        this.seasonName = seasonName;
        this.byFarm = byFarm;
        this.byCrop = byCrop;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public List<SeasonFarmReportDTO> getByFarm() {
        return byFarm;
    }

    public void setByFarm(List<SeasonFarmReportDTO> byFarm) {
        this.byFarm = byFarm;
    }

    public List<SeasonCropReportDTO> getByCrop() {
        return byCrop;
    }

    public void setByCrop(List<SeasonCropReportDTO> byCrop) {
        this.byCrop = byCrop;
    }
}

