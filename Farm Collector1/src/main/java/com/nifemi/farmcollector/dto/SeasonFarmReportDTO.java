package com.nifemi.farmcollector.dto;

public class SeasonFarmReportDTO {

    private String farmName;
    private String cropType;
    private double expectedAmount;
    private double actualAmount;

    public SeasonFarmReportDTO() {}

    public SeasonFarmReportDTO(String farmName, String cropType, double expectedAmount, double actualAmount) {
        this.farmName = farmName;
        this.cropType = cropType;
        this.expectedAmount = expectedAmount;
        this.actualAmount = actualAmount;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }
}

