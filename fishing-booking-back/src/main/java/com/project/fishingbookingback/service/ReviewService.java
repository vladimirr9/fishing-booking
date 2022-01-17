package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.model.Review;
import com.project.fishingbookingback.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReservationService reservationService;
    private final ReviewRepository reviewRepository;

    public ReviewService(ReservationService reservationService, ReviewRepository reviewRepository) {
        this.reservationService = reservationService;
        this.reviewRepository = reviewRepository;
    }

    public void createReview(Long reservationId, int mark, String comment) {
        Reservation reservation = reservationService.findByID(reservationId);
        if(reservation.getReview()!=null || reservation.getReport()==null) throw new NotAllowedException();
        Review review = new Review(reservation,mark,comment,false);
        reservation.setReview(review);
        reviewRepository.save(review);
    }
}
