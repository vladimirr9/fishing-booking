package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.BoatPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoatPromotionRepository extends JpaRepository<BoatPromotion, Long> {
    public List<BoatPromotion> findByBoat_Id(Long id);
}
