package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.AccountDeletion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDeletionRepository extends JpaRepository<AccountDeletion, Long> {
    public AccountDeletion findByEmail(String email);

    boolean existsByEmail(String email);
}
