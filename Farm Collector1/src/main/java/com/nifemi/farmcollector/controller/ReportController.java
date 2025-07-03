package com.nifemi.farmcollector.controller;

import com.farmcollector.dto.SeasonReportDTO;
import com.farmcollector.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/season/{seasonName}")
    public ResponseEntity<SeasonReportDTO> getReportBySeason(@PathVariable String seasonName) {
        SeasonReportDTO report = reportService.generateSeasonReport(seasonName);
        return ResponseEntity.ok(report);
    }
}

