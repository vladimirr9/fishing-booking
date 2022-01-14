package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.HolidayHomePromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HolidayHomePromotionRepository extends JpaRepository<HolidayHomePromotion, Long> {
    public List<HolidayHomePromotion> findByHolidayHome_Id(Long id);
}
