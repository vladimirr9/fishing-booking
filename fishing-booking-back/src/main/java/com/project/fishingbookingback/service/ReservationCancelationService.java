package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
public class ReservationCancelationService {

    private final ReservationRepository reservationRepository;
    private final AvailablePeriodService availablePeriodService;

    public ReservationCancelationService(ReservationRepository reservationRepository, AvailablePeriodService availablePeriodService) {
        this.reservationRepository = reservationRepository;
        this.availablePeriodService = availablePeriodService;
    }

    public void cancelReservation(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new EntityNotFoundException(Reservation.class.getSimpleName()));
        checkDates(reservation);
        reservationRepository.delete(reservation);
        if(reservation.getClass() == HolidayHomeReservation.class)
            cancelHomeReservation((HolidayHomeReservation) reservation);
        else if(reservation.getClass() == BoatReservation.class)
            cancelBoatReservation((BoatReservation) reservation);
        else
            cancelAdventureReservation((AdventureReservation) reservation);


    }

    private void checkDates(Reservation reservation){
        long diff = ChronoUnit.DAYS.between(LocalDateTime.now(),reservation.getStartDate());
        if(diff>=0 && diff < 3) throw new NotAllowedException();
    }

    private void cancelBoatReservation(BoatReservation reservation){
        Set<BoatReservation> reservations = reservation.getBoat().getReservations();
        reservations.remove(reservation);
        reservation.getBoat().setReservations(reservations);
        availablePeriodService.createForBoat(new AvailablePeriod(reservation.getStartDate(),reservation.getEndDate()),reservation.getBoat().getId(),reservation.getOwnerEmail());
    }

    private void cancelHomeReservation(HolidayHomeReservation reservation){
        Set<HolidayHomeReservation> reservations = reservation.getHolidayHome().getReservations();
        reservations.remove(reservation);
        reservation.getHolidayHome().setReservations(reservations);
        availablePeriodService.createForHolidayHome(new AvailablePeriod(reservation.getStartDate(),reservation.getEndDate()),reservation.getHolidayHome().getId(),reservation.getOwnerEmail());
    }

    private void cancelAdventureReservation(AdventureReservation reservation){
        Set<AdventureReservation> reservations = reservation.getAdventure().getReservations();
        reservations.remove(reservation);
        reservation.getAdventure().setReservations(reservations);
        availablePeriodService.createForInstructor(new AvailablePeriod(reservation.getStartDate(),reservation.getEndDate()),reservation.getOwnerEmail());
    }

}
