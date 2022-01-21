package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.CreateReviewDTO;
import com.project.fishingbookingback.model.Review;
import com.project.fishingbookingback.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<HttpStatus> createReview(@Valid @RequestBody CreateReviewDTO reviewDTO) {
        reviewService.createReview(reviewDTO.getReservationId(), reviewDTO.getMark(), reviewDTO.getComment());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAll() {
        return ResponseEntity.ok(reviewService.findALl());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<HttpStatus> approveReview(@PathVariable Long id) {
        reviewService.approveReview(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deny")
    public ResponseEntity<HttpStatus> denyReview(@PathVariable Long id) {
        reviewService.denyReview(id);
        return ResponseEntity.noContent().build();
    }

}
