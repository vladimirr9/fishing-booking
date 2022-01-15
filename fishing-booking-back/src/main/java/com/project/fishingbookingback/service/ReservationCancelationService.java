package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.AdventureReservation;
import com.project.fishingbookingback.model.BoatReservation;
import com.project.fishingbookingback.model.HolidayHomeReservation;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReservationCancelationService {

    private final ReservationRepository reservationRepository;

    public ReservationCancelationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void cancelReservation(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new EntityNotFoundException(Reservation.class.getSimpleName()));

        if(reservation.getClass() == HolidayHomeReservation.class)
            cancelHomeReservation((HolidayHomeReservation) reservation);
        else if(reservation.getClass() == BoatReservation.class)
            cancelBoatReservation((BoatReservation) reservation);
        else
            cancelAdventureReservation((AdventureReservation) reservation);

        reservationRepository.delete(reservation);
    }

    //IMPLEMENT
    private void cancelBoatReservation(BoatReservation reservation){
        Set<BoatReservation> reservations = reservation.getBoat().getReservations();
        reservations.remove(reservation);
        reservation.getBoat().setReservations(reservations);
    }

    private void cancelHomeReservation(HolidayHomeReservation reservation){
        Set<HolidayHomeReservation> reservations = reservation.getHolidayHome().getReservations();
        reservations.remove(reservation);
        reservation.getHolidayHome().setReservations(reservations);
    }

    private void cancelAdventureReservation(AdventureReservation reservation){
        Set<AdventureReservation> reservations = reservation.getAdventure().getReservations();
        reservations.remove(reservation);
        reservation.getAdventure().setReservations(reservations);
    }

}
