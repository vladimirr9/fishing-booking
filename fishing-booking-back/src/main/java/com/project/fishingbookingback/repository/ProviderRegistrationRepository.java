package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderRegistrationRepository extends JpaRepository<ProviderRegistration, Long> {
    public ProviderRegistration findByEmail(String email);

    @Query("select pr from ProviderRegistration pr where (:excludedRole is null or pr.role <> :excludedRole)")
    public List<ProviderRegistration> findAllExcept(@Param("excludedRole") Role excludedRole);
}
