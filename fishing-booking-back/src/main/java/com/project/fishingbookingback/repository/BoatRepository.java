package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, Long> {
}
