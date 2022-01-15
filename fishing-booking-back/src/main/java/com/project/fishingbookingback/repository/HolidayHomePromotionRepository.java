package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.FishingPromotion;
import com.project.fishingbookingback.model.HolidayHomePromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface HolidayHomePromotionRepository extends JpaRepository<HolidayHomePromotion, Long> {
    public List<HolidayHomePromotion> findByHolidayHome_Id(Long id);

    @Query("select hhp from HolidayHomePromotion hhp where (:email is null or hhp.holidayHome.homeOwner.email = :email)")
    Collection<HolidayHomePromotion> getAllForHomeOwner(String email);
}
