package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
