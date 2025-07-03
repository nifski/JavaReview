package com.nifemi.farmcollector.service;

import com.farmcollector.dto.SeasonReportDTO;

public interface ReportService {
    SeasonReportDTO generateSeasonReport(String seasonName);
}