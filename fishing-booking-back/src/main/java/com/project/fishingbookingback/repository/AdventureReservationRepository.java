package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.AdventureReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {
}
