package com.project.fishingbookingback.repository;


import com.project.fishingbookingback.model.HolidayHomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayHomeReservationRepository extends JpaRepository<HolidayHomeReservation, Long> {
}
