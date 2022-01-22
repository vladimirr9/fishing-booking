package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.FishingAdventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface AdventureRepository extends JpaRepository<FishingAdventure, Long> {
    @Query("select fa from FishingAdventure fa where (:instructorUsername is null or fa.fishingInstructor.email = :instructorUsername)\n" +
            "and (:adventureName is null or upper(fa.name) like upper(concat('%', :adventureName,'%')))")
    public List<FishingAdventure> findByFishingInstructorId(@Param("instructorUsername") String instructorUsername,
                                                            @Param("adventureName") String adventureName);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select h from FishingAdventure h where h.id=:id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout",value="0")})
    public FishingAdventure findOneById(@Param("id")Long id);
}
