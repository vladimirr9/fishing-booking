package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.AccountDeletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface AccountDeletionRepository extends JpaRepository<AccountDeletion, Long> {
    public AccountDeletion findByEmail(String email);

    boolean existsByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select ac from AccountDeletion ac where ac.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    public AccountDeletion findOneById(@Param("id") Long id);
}
