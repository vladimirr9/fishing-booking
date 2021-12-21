package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.AvailablePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, Long> {

    @Query("select ap from AvailablePeriod ap where (:providerEmail is null or ap.email = :providerEmail)")
    List<AvailablePeriod> findAll(@Param("providerEmail") String providerEmail);
}
