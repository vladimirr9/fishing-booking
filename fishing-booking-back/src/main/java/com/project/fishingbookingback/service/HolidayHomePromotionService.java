package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.OverlapsException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.HolidayHomePromotionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class HolidayHomePromotionService {

    private final HolidayHomePromotionRepository repository;
    private final AvailablePeriodService availablePeriodService;
    private final ReservationService reservationService;
    private final LoggedUserService loggedUserService;

    public HolidayHomePromotionService(HolidayHomePromotionRepository repository, @Lazy AvailablePeriodService availablePeriodService, @Lazy ReservationService reservationService, LoggedUserService loggedUserService) {
        this.repository = repository;
        this.availablePeriodService = availablePeriodService;
        this.reservationService = reservationService;
        this.loggedUserService = loggedUserService;
    }

    public HolidayHomePromotion findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(HolidayHomePromotion.class.getSimpleName()));
    }


    public HolidayHomePromotion addPromotion(HolidayHomePromotion holidayHomePromotion) {
        Collection<AvailablePeriod> availablePeriods = availablePeriodService.getPeriods(holidayHomePromotion.getHolidayHome().getHomeOwner().getEmail()).stream().filter(x -> x.getHolidayHome() != null && x.getHolidayHome().getId().equals(holidayHomePromotion.getHolidayHome().getId())).toList();
        Collection<Reservation> reservations = reservationService.getAllForBoat(holidayHomePromotion.getHolidayHome().getId());
        Collection<HolidayHomePromotion> promotions = repository.findByHolidayHome_Id(holidayHomePromotion.getHolidayHome().getId());
        checkIfOverlaps(holidayHomePromotion, availablePeriods, reservations, promotions);

        return repository.save(holidayHomePromotion);
    }

    public void deletePromotion(Long id_promotion) {
        findByID(id_promotion);
        repository.deleteById(id_promotion);
    }

    public List<HolidayHomePromotion> getPromotions(Long id) {
        return repository.findByHolidayHome_Id(id);
    }
    public Collection<HolidayHomePromotion> getAllForHomeOwner(String email) { return  repository.getAllForHomeOwner(email);}

    private void checkIfOverlaps(HolidayHomePromotion homePromotion, Collection<AvailablePeriod> availablePeriods, Collection<Reservation> reservations, Collection<HolidayHomePromotion> promotions) {
        for (var availablePeriod: availablePeriods) {
            if (homePromotion.overlaps(availablePeriod)) {
                throw new OverlapsException(availablePeriod.getClass().getSimpleName(), availablePeriod.getId());
            }
        }
        for (var reservation: reservations) {
            if (reservation.isApproved() && homePromotion.overlaps(reservation)) {
                throw new OverlapsException(reservation.getClass().getSimpleName(), reservation.getId());
            }
        }
        for (var promotion: promotions) {
            if (homePromotion.overlaps(promotion)) {
                throw new OverlapsException(promotion.getClass().getSimpleName(), promotion.getId());
            }
        }
    }

}