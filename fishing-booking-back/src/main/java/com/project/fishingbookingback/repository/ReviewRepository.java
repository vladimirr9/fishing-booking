package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
