package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
