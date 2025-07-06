package com.nifemi.farmcollector.service;

import com.nifemi.farmcollector.dto.SeasonReportDTO;

public interface ReportService {
    SeasonReportDTO generateSeasonReport(String seasonName);
}