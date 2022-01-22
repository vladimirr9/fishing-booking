package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.HolidayHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface BoatRepository extends JpaRepository<Boat, Long> {
    @Query("select b from Boat b where (:ownerUsername is null or b.boatOwner.email = :ownerUsername)\n" +
            "and (:boatSearch is null or upper(b.name) like upper(concat('%', :boatSearch,'%')))")
    List<Boat> findByOwnerId(String ownerUsername, String boatSearch);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select b from Boat b where b.id=:id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout",value="0")})
    public Boat findOneById(@Param("id")Long id);
}
