package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReservationService reservationService;
    private final ReviewRepository reviewRepository;
    private final AdventureService adventureService;
    private final HolidayHomeService holidayHomeService;
    private final BoatService boatService;
    private final EmailService emailService;

    public ReviewService(ReservationService reservationService, ReviewRepository reviewRepository, AdventureService adventureService, HolidayHomeService holidayHomeService, BoatService boatService, EmailService emailService) {
        this.reservationService = reservationService;
        this.reviewRepository = reviewRepository;
        this.adventureService = adventureService;
        this.holidayHomeService = holidayHomeService;
        this.boatService = boatService;
        this.emailService = emailService;
    }

    public void createReview(Long reservationId, int mark, String comment) {
        Reservation reservation = reservationService.findByID(reservationId);
        if (reservation.getReview() != null || reservation.getReport() == null) throw new NotAllowedException();
        Review review = new Review(reservation, mark, comment, false);
        reservation.setReview(review);
        reviewRepository.save(review);
    }

    public List<Review> findALl() {
        return reviewRepository.findAll();
    }

    public Review findByID(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Review.class.getSimpleName()));
    }

    public void approveReview(Long id) {
        Review review = findByID(id);
        review.setApproved(true);
        reviewRepository.save(review);
        if (review.getReservation().getClass() == AdventureReservation.class) {
            updateFishingAdventure(review);
        } else if (review.getReservation().getClass() == BoatReservation.class) {
            updateBoat(review);
        } else {
            updateHome(review);
        }
        emailService.sendSimpleMessage(review.getReservation().getOwnerEmail(), "New Review", review.getComment());
    }

    public void denyReview(Long id) {
        Review review = findByID(id);
        reservationService.denyReview(review.getReservation().getId());
    }

    public void updateReview(Review review){
        reviewRepository.save(review);
    }

    private void updateFishingAdventure(Review review) {
        AdventureReservation adventureReservation = (AdventureReservation) review.getReservation();
        Double average = 0d;
        int count = 0;
        for (Review rev : findALl()) {
            if (rev.getReservation().getClass() == AdventureReservation.class) {
                AdventureReservation ar = (AdventureReservation) rev.getReservation();
                if (ar.getAdventure().getId() == adventureReservation.getAdventure().getId()) {
                    average += rev.getMark();
                    count++;
                }
            }
        }
        average = count > 0 ? average / count : 0;
        adventureService.updateAverageMark(adventureReservation.getId(), average);
    }

    private void updateBoat(Review review) {
        BoatReservation boatReservation = (BoatReservation) review.getReservation();
        Double average = 0d;
        int count = 0;
        for (Review rev : findALl()) {
            if (rev.getReservation().getClass() == BoatReservation.class) {
                BoatReservation ar = (BoatReservation) rev.getReservation();
                if (ar.getBoat().getId() == boatReservation.getBoat().getId()) {
                    average += rev.getMark();
                    count++;
                }
            }
        }
        average = count > 0 ? average / count : 0;
        boatService.updateAverageMark(boatReservation.getId(), average);
    }

    private void updateHome(Review review) {
        HolidayHomeReservation homeReservation = (HolidayHomeReservation) review.getReservation();
        Double average = 0d;
        int count = 0;
        for (Review rev : findALl()) {
            if (rev.getReservation().getClass() == HolidayHomeReservation.class) {
                HolidayHomeReservation ar = (HolidayHomeReservation) rev.getReservation();
                if (ar.getHolidayHome().getId() == homeReservation.getHolidayHome().getId()) {
                    average += rev.getMark();
                    count++;
                }
            }
        }
        average = count > 0 ? average / count : 0;
        holidayHomeService.updateAverageMark(homeReservation.getId(), average);
    }
}
