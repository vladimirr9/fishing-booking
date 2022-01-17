package com.project.fishingbookingback.repository;


import com.project.fishingbookingback.model.HolidayHomePromotion;
import com.project.fishingbookingback.model.HolidayHomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HolidayHomeReservationRepository extends JpaRepository<HolidayHomeReservation, Long> {
    @Query("select r from HolidayHomeReservation r where (:id is null or (r.holidayHome is not null and r.holidayHome.id = :id))")
    public List<HolidayHomeReservation> findByHolidayHome_Id(Long id);
}
