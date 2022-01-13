package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
