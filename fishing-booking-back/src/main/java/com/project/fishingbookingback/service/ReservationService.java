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
import java.util.Objects;

@Service
public class ReservationService {
    private final AdventureReservationRepository adventureReservationRepository;
    private final HolidayHomeReservationRepository holidayHomeReservationRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final ReservationRepository reservationRepository;
    private final ReportService reportService;
    private final EmailService emailService;

    public ReservationService(AdventureReservationRepository adventureReservationRepository, HolidayHomeReservationRepository holidayHomeReservationRepository, BoatReservationRepository boatReservationRepository, ReservationRepository reservationRepository, ReportService reportService, EmailService emailService) {
        this.adventureReservationRepository = adventureReservationRepository;
        this.holidayHomeReservationRepository = holidayHomeReservationRepository;
        this.boatReservationRepository = boatReservationRepository;
        this.reservationRepository = reservationRepository;
        this.reportService = reportService;
        this.emailService = emailService;
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

    public void approveReport(Long id, Long report_id, String message) {
        Reservation reservation = findByID(id);
        Report report = reservation.getReport();
        if (Objects.equals(report.getId(), report_id)) {
            report.setApproved(true);
        }
        reservationRepository.save(reservation);
        String clientEmail = report.getReservation().getClient().getEmail();
        String serviceProviderEmail = report.getReservation().getOwnerEmail();
        emailService.sendSimpleMessage(clientEmail, "Fishing Booking Account Sanctioned", message);
        emailService.sendSimpleMessage(serviceProviderEmail, "Fishing Booking Client Sanctioned", message);
    }

    public void denyReport(Long id, Long report_id, String message) {
        Reservation reservation = findByID(id);
        Report report = reservation.getReport();
        if (Objects.equals(report.getId(), report_id)) {
            reservation.setReport(null);
        }
        reservationRepository.save(reservation);
        String clientEmail = report.getReservation().getClient().getEmail();
        String serviceProviderEmail = report.getReservation().getOwnerEmail();
        emailService.sendSimpleMessage(clientEmail, "Fishing Booking Account Sanctioned", message);
        emailService.sendSimpleMessage(serviceProviderEmail, "Fishing Booking Client Sanctioned", message);
    }
}
