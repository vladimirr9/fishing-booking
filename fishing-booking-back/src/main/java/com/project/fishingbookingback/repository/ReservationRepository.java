package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select res from Reservation res where (:email is null or (res.getOwnerEmail() = :email))")
    List<Reservation> getAllForOwner(@Param("email") String email);
}
