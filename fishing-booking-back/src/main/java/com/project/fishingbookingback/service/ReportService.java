package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.Report;
import com.project.fishingbookingback.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }
    
    public void delete(Report report) {
        repository.deleteById(report.getId());
    }
}
