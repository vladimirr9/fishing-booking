package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.FishingPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FishingPromotionRepository extends JpaRepository<FishingPromotion, Long> {
    public List<FishingPromotion> findByFishingAdventure_Id(Long id);
}
