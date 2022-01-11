package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.HolidayHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoatRepository extends JpaRepository<Boat, Long> {
    @Query("select b from Boat b where (:ownerUsername is null or b.boatOwner.email = :ownerUsername)\n" +
            "and (:boatSearch is null or upper(b.name) like upper(concat('%', :boatSearch,'%')))")
    List<Boat> findByOwnerId(String ownerUsername, String boatSearch);
}
