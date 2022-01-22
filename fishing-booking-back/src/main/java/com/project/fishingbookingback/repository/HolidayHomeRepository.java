package com.project.fishingbookingback.repository;


import com.project.fishingbookingback.model.HolidayHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface HolidayHomeRepository extends JpaRepository<HolidayHome, Long> {
    @Query("select hh from HolidayHome hh where (:homeOwnerUsername is null or hh.homeOwner.email = :homeOwnerUsername)\n" +
            "and (:holidayHomeName is null or upper(hh.name) like upper(concat('%', :holidayHomeName,'%')))")
    public List<HolidayHome> findByHomeOwnerId(@Param("homeOwnerUsername") String homeOwnerUsername,
                                                            @Param("holidayHomeName") String holidayHomeName);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select h from HolidayHome h where h.id=:id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout",value="0")})
    public HolidayHome findOneById(@Param("id")Long id);
}