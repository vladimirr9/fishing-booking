package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
