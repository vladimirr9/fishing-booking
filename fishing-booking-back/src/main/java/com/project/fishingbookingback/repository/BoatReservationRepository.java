package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.BoatPromotion;
import com.project.fishingbookingback.model.BoatReservation;
import com.project.fishingbookingback.model.HolidayHomePromotion;
import com.project.fishingbookingback.model.HolidayHomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long> {
    @Query("select r from BoatReservation r where (:id is null or (r.boat is not null and r.boat.id = :id))")
    public List<BoatReservation> findByBoat_Id(Long id);
}
