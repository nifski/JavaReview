package com.nifemi.farmcollector.dto;

public class SeasonCropReportDTO {

    private String cropType;
    private double totalExpected;
    private double totalActual;

    public SeasonCropReportDTO() {}

    public SeasonCropReportDTO(String cropType, double totalExpected, double totalActual) {
        this.cropType = cropType;
        this.totalExpected = totalExpected;
        this.totalActual = totalActual;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public double getTotalExpected() {
        return totalExpected;
    }

    public void setTotalExpected(double totalExpected) {
        this.totalExpected = totalExpected;
    }

    public double getTotalActual() {
        return totalActual;
    }

    public void setTotalActual(double totalActual) {
        this.totalActual = totalActual;
    }
}

