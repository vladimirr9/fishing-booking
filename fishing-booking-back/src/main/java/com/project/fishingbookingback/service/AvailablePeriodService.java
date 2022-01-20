package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NewAvailablePeriodOverlapsException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.AvailablePeriodRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final HolidayHomeService holidayHomeService;
    private final BoatService boatService;

    public AvailablePeriodService(AvailablePeriodRepository repository, LoggedUserService loggedUserService, UserService userService, ReservationService reservationService, FishingPromotionService fishingPromotionService, HolidayHomePromotionService holidayHomePromotionService, BoatPromotionService boatPromotionService, HolidayHomeService holidayHomeService, BoatService boatService) {
        this.repository = repository;
        this.loggedUserService = loggedUserService;
        this.userService = userService;
        this.reservationService = reservationService;
        this.fishingPromotionService = fishingPromotionService;
        this.holidayHomePromotionService = holidayHomePromotionService;
        this.boatPromotionService = boatPromotionService;
        this.holidayHomeService = holidayHomeService;
        this.boatService = boatService;
    }

    public AvailablePeriod createForInstructor(AvailablePeriod newAvailablePeriod, String email) {
        //checkIfAllowed(email);
        Collection<AvailablePeriod> availablePeriods = repository.findAllForInstructor(email);
        Collection<Reservation> reservations = reservationService.getAllForInstructor(email);
        Collection<Promotion> promotions = fishingPromotionService.getAllForInstructor(email).stream().map(e -> (Promotion)e).collect(Collectors.toList());
        checkIfOverlaps(newAvailablePeriod, availablePeriods, reservations, promotions);
        var mergedPeriod = mergeNewAvailablePeriodInstructor(newAvailablePeriod, email);
        userService.addAvailablePeriod(email, mergedPeriod);
        return mergedPeriod;
    }


    public AvailablePeriod createForHolidayHome(AvailablePeriod newAvailablePeriod, Long homeId, String email) {
        //checkIfAllowed(email);
        Collection<AvailablePeriod> availablePeriods = repository.findAllForHome(homeId);
        Collection<Reservation> reservations = reservationService.getAllForHome(homeId);
        Collection<Promotion> promotions = holidayHomePromotionService.getPromotions(homeId).stream().map(e -> (Promotion)e).collect(Collectors.toList());
        checkIfOverlaps(newAvailablePeriod, availablePeriods, reservations, promotions);
        var mergedPeriod = mergeNewAvailablePeriodHome(newAvailablePeriod, homeId);
        holidayHomeService.addAvailablePeriod(homeId, newAvailablePeriod);
        return mergedPeriod;
    }



    public AvailablePeriod createForBoat(AvailablePeriod newAvailablePeriod, Long boatId, String email) {
       // checkIfAllowed(email);
        Collection<AvailablePeriod> availablePeriods = repository.findAllForBoat(boatId);
        Collection<Reservation> reservations = reservationService.getAllForBoat(boatId);
        Collection<Promotion> promotions = boatPromotionService.getPromotions(boatId).stream().map(e -> (Promotion)e).collect(Collectors.toList());
        checkIfOverlaps(newAvailablePeriod, availablePeriods, reservations, promotions);
        var mergedPeriod = mergeNewAvailablePeriodBoat(newAvailablePeriod, boatId);
        boatService.addAvailablePeriod(boatId, mergedPeriod);
        return mergedPeriod;
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
             if (reservation.isApproved() && newAvailablePeriod.overlaps(reservation)) {
                 throw new NewAvailablePeriodOverlapsException(reservation.getClass().getSimpleName(), reservation.getId());
             }
         }
         for (var promotion: promotions) {
            if (newAvailablePeriod.overlaps(promotion)) {
                throw new NewAvailablePeriodOverlapsException(promotion.getClass().getSimpleName(), promotion.getId());
            }
         }
    }

    public void occupyBy(Reservation reservation) {
        Collection<AvailablePeriod> periods;
        AvailablePeriod period;
        if(reservation.getClass() == HolidayHomeReservation.class) {
            var homeReservation = (HolidayHomeReservation) reservation;
            var holidayHome = homeReservation.getHolidayHome();
            periods = repository.findAllForHome(holidayHome.getId());
            period = getPeriodForReservation(periods, reservation);
            if (period == null) throw new RuntimeException();
            holidayHome.getAvailablePeriods().remove(period);
            repository.delete(period);
            var sliceBefore = period.sliceBefore(reservation.getStartDate());
            var sliceAfter = period.sliceAfter(reservation.getEndDate());
            if(sliceBefore != null)
            {
                holidayHome.getAvailablePeriods().add(sliceBefore);
                repository.save(sliceBefore);
            }
            if(sliceAfter != null)
            {
                holidayHome.getAvailablePeriods().add(sliceAfter);
                repository.save(sliceAfter);
            }
        }
        else if(reservation.getClass() == BoatReservation.class) {
            var boatReservation = (BoatReservation) reservation;
            var boat = boatReservation.getBoat();
            periods = repository.findAllForBoat(boat.getId());
            period = getPeriodForReservation(periods, reservation);
            if (period == null) throw new RuntimeException();
            boat.getAvailablePeriods().remove(period);
            repository.delete(period);
            var sliceBefore = period.sliceBefore(reservation.getStartDate());
            var sliceAfter = period.sliceAfter(reservation.getEndDate());
            if(sliceBefore != null)
            {
                boat.getAvailablePeriods().add(sliceBefore);
                repository.save(sliceBefore);
            }
            if(sliceAfter != null)
            {
                boat.getAvailablePeriods().add(sliceAfter);
                repository.save(sliceAfter);
            }
        }
        else {
            var adventureReservation = (AdventureReservation) reservation;
            var instructor = adventureReservation.getAdventure().getFishingInstructor();
            periods = repository.findAllForInstructor(instructor.getEmail());
            period = getPeriodForReservation(periods, reservation);
            if (period == null) throw new RuntimeException();
            instructor.getAvailablePeriods().remove(period);
            repository.delete(period);
            var sliceBefore = period.sliceBefore(reservation.getStartDate());
            var sliceAfter = period.sliceAfter(reservation.getEndDate());
            if(sliceBefore != null)
            {
                instructor.getAvailablePeriods().add(sliceBefore);
                repository.save(sliceBefore);
            }
            if(sliceAfter != null)
            {
                instructor.getAvailablePeriods().add(sliceAfter);
                repository.save(sliceAfter);
            }
        }
    }


    private AvailablePeriod mergeNewAvailablePeriodInstructor(AvailablePeriod newAvailablePeriod, String email) {
        for (var period: repository.findAllForInstructor(email)) {
            if(newAvailablePeriod.extendWith(period)) {
                repository.delete(period);
            }
        }
        return newAvailablePeriod;
    }

    private AvailablePeriod mergeNewAvailablePeriodHome(AvailablePeriod newAvailablePeriod, Long homeId) {
        for (var period: repository.findAllForHome(homeId)) {
            if(newAvailablePeriod.extendWith(period)) {
                repository.delete(period);
            }
        }
        return newAvailablePeriod;
    }

    private AvailablePeriod mergeNewAvailablePeriodBoat(AvailablePeriod newAvailablePeriod, Long boatId) {
        for (var period: repository.findAllForBoat(boatId)) {
            if(newAvailablePeriod.extendWith(period)) {
                repository.delete(period);
            }
        }
        return newAvailablePeriod;
    }

    private AvailablePeriod getPeriodForReservation(Collection<AvailablePeriod> periods, Reservation reservation) {
        for (var period: periods) {
            if (period.availableForReservation(reservation)) {
                return period;
            }
        }
        return null;
    }
}
