package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.ServiceFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<ServiceFee, Long> {
}
