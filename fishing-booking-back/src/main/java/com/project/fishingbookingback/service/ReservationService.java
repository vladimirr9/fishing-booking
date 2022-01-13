package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.Report;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.AdventureReservationRepository;
import com.project.fishingbookingback.repository.BoatReservationRepository;
import com.project.fishingbookingback.repository.HolidayHomeReservationRepository;
import com.project.fishingbookingback.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final AdventureReservationRepository adventureReservationRepository;
    private final HolidayHomeReservationRepository holidayHomeReservationRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final ReservationRepository reservationRepository;
    private final ReportService reportService;

    public ReservationService(AdventureReservationRepository adventureReservationRepository, HolidayHomeReservationRepository holidayHomeReservationRepository, BoatReservationRepository boatReservationRepository, ReservationRepository reservationRepository, ReportService reportService) {
        this.adventureReservationRepository = adventureReservationRepository;
        this.holidayHomeReservationRepository = holidayHomeReservationRepository;
        this.boatReservationRepository = boatReservationRepository;
        this.reservationRepository = reservationRepository;
        this.reportService = reportService;
    }

    public List<Reservation> getAll() {
        /*List<Reservation> reservations = new ArrayList<>();
        reservations.addAll(adventureReservationRepository.findAll());
        reservations.addAll(holidayHomeReservationRepository.findAll());
        reservations.addAll(boatReservationRepository.findAll());
        return reservations;*/
        return reservationRepository.findAll();
    }

    public Reservation findByID(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Reservation.class.getSimpleName()));
    }

    public Report addReport(Long id, Report report) {
        Reservation reservation = findByID(id);
        reservation.setReport(report);
        report.setReservation(reservation);
        if (!report.isSanction()) {
            report.setApproved(true);
        }
        Reservation changedReservation = reservationRepository.save(reservation);
        return changedReservation.getReport();
    }
}
