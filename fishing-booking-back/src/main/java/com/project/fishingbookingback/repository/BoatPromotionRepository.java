package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.BoatPromotion;
import com.project.fishingbookingback.model.HolidayHomePromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface BoatPromotionRepository extends JpaRepository<BoatPromotion, Long> {
    public List<BoatPromotion> findByBoat_Id(Long id);

    @Query("select bp from BoatPromotion bp where (:email is null or bp.boat.boatOwner.email = :email)")
    Collection<BoatPromotion> getAllForBoatOwner(String email);
}
