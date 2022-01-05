package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.BoatReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long> {
}
