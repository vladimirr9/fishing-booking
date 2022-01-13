package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.Report;
import com.project.fishingbookingback.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository repository;
    private final EmailService emailService;

    public ReportService(ReportRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public void delete(Report report) {
        repository.deleteById(report.getId());
    }

    public List<Report> findAll(Boolean approved) {
        return repository.findAllReports(approved);
    }

    public Report findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Report.class.getSimpleName()));
    }
    
}
