package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.AvailablePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, Long> {

    @Query("select ap from AvailablePeriod ap where (:email is null or ap.fishingInstructor.email = :email)")
    List<AvailablePeriod> findAll(@Param("email") String email);
}
