package com.project.fishingbookingback.service;


import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.OverlapsException;
import com.project.fishingbookingback.model.AvailablePeriod;
import com.project.fishingbookingback.model.FishingPromotion;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.FishingPromotionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FishingPromotionService {

    private final FishingPromotionRepository repository;
    private final AvailablePeriodService availablePeriodService;
    private final ReservationService reservationService;
    private final LoggedUserService loggedUserService;

    public FishingPromotionService(FishingPromotionRepository repository, @Lazy AvailablePeriodService availablePeriodService, @Lazy ReservationService reservationService, LoggedUserService loggedUserService) {
        this.repository = repository;
        this.availablePeriodService = availablePeriodService;
        this.reservationService = reservationService;
        this.loggedUserService = loggedUserService;
    }

    public FishingPromotion findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(FishingPromotion.class.getSimpleName()));
    }


    public FishingPromotion addPromotion(FishingPromotion fishingPromotion) {
        Collection<AvailablePeriod> availablePeriods = availablePeriodService.getPeriods(fishingPromotion.getFishingAdventure().getFishingInstructor().getEmail()).stream().filter(x -> x.getFishingInstructor() != null && x.getFishingInstructor().getId().equals(fishingPromotion.getFishingAdventure().getFishingInstructor().getId())).toList();
        Collection<Reservation> reservations = reservationService.getAllForInstructor(fishingPromotion.getFishingAdventure().getFishingInstructor().getEmail());
        Collection<FishingPromotion> promotions = repository.findByFishingAdventure_Id(fishingPromotion.getFishingAdventure().getId());
        checkIfOverlaps(fishingPromotion, availablePeriods, reservations, promotions);
        return repository.save(fishingPromotion);
    }

    public void deletePromotion(Long id_promotion) {
        findByID(id_promotion);
        repository.deleteById(id_promotion);
    }

    public List<FishingPromotion> getPromotions(Long id) {
        List<FishingPromotion> retList = repository.findByFishingAdventure_Id(id);
        return retList;
    }

    public Collection<FishingPromotion> getAllForInstructor(String email) { return  repository.getAllForInstructor(email);}

    private void checkIfOverlaps(FishingPromotion fishingPromotion, Collection<AvailablePeriod> availablePeriods, Collection<Reservation> reservations, Collection<FishingPromotion> promotions) {
        for (var availablePeriod: availablePeriods) {
            if (fishingPromotion.overlaps(availablePeriod)) {
                throw new OverlapsException(availablePeriod.getClass().getSimpleName(), availablePeriod.getId());
            }
        }
        for (var reservation: reservations) {
            if (reservation.isApproved() && fishingPromotion.overlaps(reservation)) {
                throw new OverlapsException(reservation.getClass().getSimpleName(), reservation.getId());
            }
        }
        for (var promotion: promotions) {
            if (fishingPromotion.overlaps(promotion)) {
                throw new OverlapsException(promotion.getClass().getSimpleName(), promotion.getId());
            }
        }
    }

}
