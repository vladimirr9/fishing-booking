package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select c from Complaint c where c.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    public Complaint findOneById(@Param("id") Long id);
}
