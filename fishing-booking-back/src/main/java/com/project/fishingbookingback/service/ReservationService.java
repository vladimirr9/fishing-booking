package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.exception.UnrecognizedTypeException;
import com.project.fishingbookingback.model.AdventureReservation;
import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.BoatReservation;
import com.project.fishingbookingback.model.Client;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.HolidayHome;
import com.project.fishingbookingback.model.HolidayHomeReservation;
import com.project.fishingbookingback.model.Report;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReportService reportService;
    private final EmailService emailService;
    private final HolidayHomeService holidayHomeService;
    private final UserService userService;
    private final BoatService boatService;
    private final AdventureService adventureService;

    public ReservationService(ReservationRepository reservationRepository, ReportService reportService, EmailService emailService, HolidayHomeService holidayHomeService, UserService userService, BoatService boatService, AdventureService adventureService) {
        this.reservationRepository = reservationRepository;
        this.reportService = reportService;
        this.emailService = emailService;
        this.holidayHomeService = holidayHomeService;
        this.userService = userService;
        this.boatService = boatService;
        this.adventureService = adventureService;
    }


    public List<Reservation> getAll(String ownerEmail) {
        List<Reservation> reservations = reservationRepository.findAll();
        if (ownerEmail != null) {
            reservations.removeIf(n -> (!n.getOwnerEmail().equals(ownerEmail)));
        }
        return reservations;

    }


    public Collection<Reservation> getAllForOwner(String email) {
        return reservationRepository.getAllForOwner(email);
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public void createReservation(double price, LocalDateTime from, LocalDateTime to, String clientUsername, Long entityId, String type) {
        if(from.isBefore(LocalDateTime.now())) throw new NotAllowedException();
        if (to.isBefore(from)) throw new NotAllowedException();
        Reservation reservation = createReservationdownClass(type, entityId);
        Client client = (Client) userService.findByEmail(clientUsername);
        reservation.setClient(client);
        reservation.setPrice(price);
        reservation.setStartDate(from);
        reservation.setEndDate(to);
        reservation.setApproved(false);
        reservationRepository.save(reservation);
        emailService.sendSimpleMessage(clientUsername, "Reservation", "Reservation request successfully sent!");
    }


    private Reservation createReservationdownClass(String type, Long entityId) {
        switch (type) {
            case "ADVENTURE" -> {

                var adventureReservation = new AdventureReservation();
                FishingAdventure adventure = adventureService.findByID(entityId);
                adventureReservation.setAdventure(adventure);
                Set<AdventureReservation> adventureReservations = adventure.getReservations();
                adventureReservations.add(adventureReservation);
                adventure.setReservations(adventureReservations);
                return adventureReservation;
            }
            case "HOLIDAY_HOME" -> {
                var holidayReservation = new HolidayHomeReservation();
                HolidayHome home = holidayHomeService.findByID(entityId);
                holidayReservation.setHolidayHome(home);
                Set<HolidayHomeReservation> holidayHomeReservations = home.getReservations();
                holidayHomeReservations.add(holidayReservation);
                home.setReservations(holidayHomeReservations);
                return holidayReservation;
            }
            case "BOAT" -> {
                var boatReservation = new BoatReservation();
                Boat boat = boatService.findByID(entityId);
                boatReservation.setBoat(boat);
                Set<BoatReservation> boatReservations = boat.getReservations();
                boatReservations.add(boatReservation);
                boat.setReservations(boatReservations);
                return boatReservation;
            }
            default -> {
                throw new UnrecognizedTypeException("Requested reservation type doesn't exist!");
            }
        }


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
        emailService.sendSimpleMessage(clientEmail, "Fishing Booking Account Not Sanctioned", message);
        emailService.sendSimpleMessage(serviceProviderEmail, "Fishing Booking Client Not Sanctioned", message);
    }
}
