package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.CreateReviewDTO;
import com.project.fishingbookingback.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createReview(@Valid @RequestBody CreateReviewDTO reviewDTO){
        reviewService.createReview(reviewDTO.getReservationId(),reviewDTO.getMark(),reviewDTO.getComment());
        return ResponseEntity.noContent().build();
    }

}
