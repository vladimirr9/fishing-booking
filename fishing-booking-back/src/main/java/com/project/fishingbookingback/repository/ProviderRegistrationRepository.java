package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ProviderRegistrationRepository extends JpaRepository<ProviderRegistration, Long> {
    public ProviderRegistration findByEmail(String email);

    @Query("select pr from ProviderRegistration pr where (:excludedRole is null or pr.role <> :excludedRole)")
    public List<ProviderRegistration> findAllExcept(@Param("excludedRole") Role excludedRole);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select pr from ProviderRegistration pr where pr.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    public ProviderRegistration findOneById(@Param("id") Long id);
}
