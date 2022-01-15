package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NewAvailablePeriodOverlapsException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.AvailablePeriodRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailablePeriodService {
    private final AvailablePeriodRepository repository;
    private final LoggedUserService loggedUserService;
    private final UserService userService;
    private final ReservationService reservationService;
    private final FishingPromotionService fishingPromotionService;
    private final HolidayHomePromotionService holidayHomePromotionService;
    private final BoatPromotionService boatPromotionService;

    public AvailablePeriodService(AvailablePeriodRepository repository, LoggedUserService loggedUserService, UserService userService, ReservationService reservationService, FishingPromotionService fishingPromotionService, HolidayHomePromotionService holidayHomePromotionService, BoatPromotionService boatPromotionService) {
        this.repository = repository;
        this.loggedUserService = loggedUserService;
        this.userService = userService;
        this.reservationService = reservationService;
        this.fishingPromotionService = fishingPromotionService;
        this.holidayHomePromotionService = holidayHomePromotionService;
        this.boatPromotionService = boatPromotionService;
    }

    public AvailablePeriod createForInstructor(AvailablePeriod newAvailablePeriod, String email) {
        checkIfAllowed(email);
        Collection<AvailablePeriod> availablePeriods = repository.findAllForInstructor(email);
        Collection<Reservation> reservations = reservationService.getAllForOwner(email);
        Collection<Promotion> promotions = fishingPromotionService.getAllForInstructor(email).stream().map(e -> (Promotion)e).collect(Collectors.toList());
        checkIfOverlaps(newAvailablePeriod, availablePeriods, reservations, promotions);
        AvailablePeriod savedAvailablePeriod = repository.save(newAvailablePeriod);
        userService.addAvailablePeriod(email, savedAvailablePeriod);
        return savedAvailablePeriod;
    }




    public AvailablePeriod createForHolidayHome(AvailablePeriod newAvailablePeriod, Long homeId, String email) {
        checkIfAllowed(email);
        Collection<AvailablePeriod> availablePeriods = repository.findAllForHome(homeId);
        Collection<Reservation> reservations = reservationService.getAllForOwner(email);
        Collection<Promotion> promotions = holidayHomePromotionService.getAllForHomeOwner(email).stream().map(e -> (Promotion)e).collect(Collectors.toList());
        checkIfOverlaps(newAvailablePeriod, availablePeriods, reservations, promotions);
        AvailablePeriod savedAvailablePeriod = repository.save(newAvailablePeriod);
        userService.addAvailablePeriod(email, savedAvailablePeriod);
        return savedAvailablePeriod;
    }

    public AvailablePeriod createForBoat(AvailablePeriod newAvailablePeriod, Long boatId, String email) {
        checkIfAllowed(email);
        Collection<AvailablePeriod> availablePeriods = repository.findAllForHome(boatId);
        Collection<Reservation> reservations = reservationService.getAllForOwner(email);
        Collection<Promotion> promotions = boatPromotionService.getAllForBoatOwner(email).stream().map(e -> (Promotion)e).collect(Collectors.toList());
        checkIfOverlaps(newAvailablePeriod, availablePeriods, reservations, promotions);
        AvailablePeriod savedAvailablePeriod = repository.save(newAvailablePeriod);
        userService.addAvailablePeriod(email, savedAvailablePeriod);
        return savedAvailablePeriod;
    }

    public List<AvailablePeriod> getPeriods(String email) {
        return repository.findAllForInstructor(email);
    }

    public AvailablePeriod findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(AvailablePeriod.class.getSimpleName()));
    }

    public void delete(Long id) {
        AvailablePeriod availablePeriod = findByID(id);
        userService.removeAvailablePeriod(availablePeriod);
    }


    private void checkIfAllowed(String email) {
        String userEmail = loggedUserService.getUsername();
        if (!userEmail.equals(email)) {
            throw new NotAllowedException();
        }
    }

    private void checkIfOverlaps(AvailablePeriod newAvailablePeriod, Collection<AvailablePeriod> availablePeriods, Collection<Reservation> reservations, Collection<Promotion> promotions) {
         for (var availablePeriod: availablePeriods) {
            if (newAvailablePeriod.overlaps(availablePeriod)) {
                throw new NewAvailablePeriodOverlapsException(availablePeriod.getClass().getSimpleName(), availablePeriod.getId());
            }
         }
         for (var reservation: reservations) {
             if (newAvailablePeriod.overlaps(reservation)) {
                 throw new NewAvailablePeriodOverlapsException(reservation.getClass().getSimpleName(), reservation.getId());
             }
         }
         for (var promotion: promotions) {
            if (newAvailablePeriod.overlaps(promotion)) {
                throw new NewAvailablePeriodOverlapsException(promotion.getClass().getSimpleName(), promotion.getId());
            }
         }
    }


}
