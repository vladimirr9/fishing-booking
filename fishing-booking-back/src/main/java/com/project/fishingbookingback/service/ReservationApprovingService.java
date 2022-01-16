package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.AdventureReservation;
import com.project.fishingbookingback.model.BoatReservation;
import com.project.fishingbookingback.model.HolidayHomeReservation;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationApprovingService {
    private final ReservationRepository reservationRepository;
    private final AvailablePeriodService availablePeriodService;

    public ReservationApprovingService(ReservationRepository reservationRepository, AvailablePeriodService availablePeriodService) {
        this.reservationRepository = reservationRepository;
        this.availablePeriodService = availablePeriodService;
    }

    public void approveReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Reservation.class.getSimpleName()));
        reservation.setApproved(true);
        availablePeriodService.occupyBy(reservation);
        removeOverlappingReservations(reservation);
        reservationRepository.save(reservation);
    }

    private void removeOverlappingReservations(Reservation reservation) {
        //TODO
    }

}
