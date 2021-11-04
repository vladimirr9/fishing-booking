package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.FishingAdventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<FishingAdventure, Long> {
}
