package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.HolidayHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HolidayHomeRepository extends JpaRepository<HolidayHome, Long> {
    @Query("select hh from HolidayHome hh where (:homeOwnerUsername is null or hh.homeOwner.email = :homeOwnerUsername)\n" +
            "and (:holidayHomeName is null or upper(hh.name) like upper(concat('%', :holidayHomeName,'%')))")
    public List<HolidayHome> findByHomeOwnerId(@Param("homeOwnerUsername") String homeOwnerUsername,
                                                            @Param("holidayHomeName") String holidayHomeName);
}