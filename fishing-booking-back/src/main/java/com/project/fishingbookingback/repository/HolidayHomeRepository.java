package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.HolidayHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HolidayHomeRepository extends JpaRepository<HolidayHome, Long> {
    @Query("select hh from HolidayHome hh where (:holidayHomeOwner is null or hh.homeOwner.email = :holidayHomeOwner)")
    public List<HolidayHome> findByHomeOwnerId(@Param("holidayHomeOwner") String holidayHomeOwner);
}
