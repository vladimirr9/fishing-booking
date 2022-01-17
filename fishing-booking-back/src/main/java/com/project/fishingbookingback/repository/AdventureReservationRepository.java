package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.AdventureReservation;
import com.project.fishingbookingback.model.BoatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {

    @Query("select r from AdventureReservation r where (:id is null or (r.adventure is not null and r.adventure.fishingInstructor.email = :email))")
    public List<BoatReservation> findByInstructorEmail(String email);
}
