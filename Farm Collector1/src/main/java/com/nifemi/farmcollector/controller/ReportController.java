package com.nifemi.farmcollector.controller;

import com.nifemi.farmcollector.dto.SeasonReportDTO;
import com.nifemi.farmcollector.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/season/{seasonName}")
    public ResponseEntity<SeasonReportDTO> getReportBySeason(@PathVariable String seasonName) {
        SeasonReportDTO report = reportService.generateSeasonReport(seasonName);
        return ResponseEntity.ok(report);
    }
}