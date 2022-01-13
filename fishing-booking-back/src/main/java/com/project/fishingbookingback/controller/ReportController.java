package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.model.Report;
import com.project.fishingbookingback.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> getReports(@RequestParam(required = false) Boolean approved) {
        return ResponseEntity.ok(reportService.findAll(approved));
    }

    /*
    @PutMapping("/{id}/approve")
    public ResponseEntity<HttpStatus> approveReport(@PathVariable Long id, @RequestBody ReportAnswerRequestDTO reportAnswerRequestDTO) {
        reportService.approve(id, reportAnswerRequestDTO.getMessage());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deny")
    public ResponseEntity<HttpStatus> denyReport(@PathVariable Long id, @RequestBody ReportAnswerRequestDTO reportAnswerRequestDTO) {
        reportService.deny(id, reportAnswerRequestDTO.getMessage());
        return ResponseEntity.noContent().build();
    }

     */
}
