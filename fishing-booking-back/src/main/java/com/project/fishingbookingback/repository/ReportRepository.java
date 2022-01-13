package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("select r from Report r where (:approved is null or r.approved = :approved)")
    List<Report> findAllReports(@Param("approved") Boolean approved);
}
