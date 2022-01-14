package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.FishingPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FishingPromotionRepository extends JpaRepository<FishingPromotion, Long> {
    public List<FishingPromotion> findByFishingAdventure_Id(Long id);

    @Query("select fp from FishingPromotion fp where (:email is null or fp.fishingAdventure.fishingInstructor.email = :email)")
    Collection<FishingPromotion> getAllForInstructor(String email);
}
