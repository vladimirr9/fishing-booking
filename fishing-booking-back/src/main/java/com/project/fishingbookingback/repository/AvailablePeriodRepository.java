package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.AvailablePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, Long> {

    @Query("select ap from AvailablePeriod ap where (:email is null or (ap.fishingInstructor is not null and ap.fishingInstructor.email = :email))")
    List<AvailablePeriod> findAllForInstructor(@Param("email") String email);

    @Query("select ap from AvailablePeriod ap where (:homeId is null or (ap.holidayHome is not null and holidayHome.id = :homeId))")
    List<AvailablePeriod> findAllForHome(@Param("homeId") Long homeId);

    @Query("select ap from AvailablePeriod ap where (:boatId is null or (ap.boat is not null and ap.boat.id = :boatId))")
    List<AvailablePeriod> findAllForBoat(@Param("boatId") Long boatId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select ap from AvailablePeriod ap where ap.id=:id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout",value="0")})
    public AvailablePeriod findOneById(@Param("id")Long id);
}
