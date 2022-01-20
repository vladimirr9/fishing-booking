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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final AvailablePeriodService availablePeriodService;
    private final LoggedUserService loggedUserService;
    private final AvailableAdventureService availableAdventureService;


    public ReservationService(ReservationRepository reservationRepository, ReportService reportService, EmailService emailService, HolidayHomeService holidayHomeService, UserService userService, BoatService boatService, AdventureService adventureService, @Lazy AvailablePeriodService availablePeriodService, LoggedUserService loggedUserService, AvailableAdventureService availableAdventureService) {
        this.reservationRepository = reservationRepository;
        this.reportService = reportService;
        this.emailService = emailService;
        this.holidayHomeService = holidayHomeService;
        this.userService = userService;
        this.boatService = boatService;
        this.adventureService = adventureService;
        this.availablePeriodService = availablePeriodService;
        this.loggedUserService = loggedUserService;
        this.availableAdventureService = availableAdventureService;
    }


    public List<Reservation> getAll(String ownerEmail) {
        List<Reservation> reservations = reservationRepository.findAll();
        if (ownerEmail != null) {
            reservations.removeIf(n -> (!n.getOwnerEmail().equals(ownerEmail)));
        }
        return reservations;

    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public void createReservation(double price, LocalDateTime from, LocalDateTime to, String clientUsername, Long entityId, String type) {
        if (from.isBefore(LocalDateTime.now())) throw new NotAllowedException();
        if (to.isBefore(from)) throw new NotAllowedException();
        Reservation reservation = createReservationdownClass(type, entityId, from, to);
        Client client = (Client) userService.findByEmail(clientUsername);
        reservation.setClient(client);
        reservation.setPrice(price);
        reservation.setStartDate(from);
        reservation.setEndDate(to);
        reservation.setApproved(false); // OVDE DODAJ!
        reservationRepository.save(reservation);
        emailService.sendSimpleMessage(clientUsername, "Reservation", "Reservation request successfully sent!");
    }


    private Reservation createReservationdownClass(String type, Long entityId, LocalDateTime from, LocalDateTime to) {
        switch (type) {
            case "ADVENTURE" -> {
                var adventureReservation = new AdventureReservation();
                FishingAdventure adventure = adventureService.findByID(entityId);
                availableAdventureService.reservePeriod(adventure.getFishingInstructor().getEmail(), from, to);
                adventureReservation.setAdventure(adventure);
                Set<AdventureReservation> adventureReservations = adventure.getReservations();
                adventureReservations.add(adventureReservation);
                adventure.setReservations(adventureReservations);
                return adventureReservation;
            }
            case "HOLIDAY_HOME" -> {
                var holidayReservation = new HolidayHomeReservation();
                HolidayHome home = holidayHomeService.findByID(entityId);
                holidayHomeService.reserveHomePeriod(entityId, from, to);
                holidayReservation.setHolidayHome(home);
                Set<HolidayHomeReservation> holidayHomeReservations = home.getReservations();
                holidayHomeReservations.add(holidayReservation);
                home.setReservations(holidayHomeReservations);
                return holidayReservation;
            }
            case "BOAT" -> {
                var boatReservation = new BoatReservation();
                Boat boat = boatService.findByID(entityId);
                boatService.reserveBoatPeriod(entityId, from, to);
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

    public Collection<Reservation> getAllForInstructor(String email) {
        var reservations = new ArrayList<Reservation>(reservationRepository.findAll());
        reservations.removeIf(x -> !((AdventureReservation) x).getAdventure().getFishingInstructor().getEmail().equals(email));
        reservations.removeIf(x -> !x.getClass().equals(AdventureReservation.class) || !((AdventureReservation) x).getAdventure().getFishingInstructor().getEmail().equals(email));
        return reservations;
    }

    public Collection<Reservation> getAllForHome(Long homeId) {
        var reservations = new ArrayList<Reservation>(reservationRepository.findAll());
        reservations.removeIf(x -> ((HolidayHomeReservation) x).getHolidayHome().getId() != homeId);
        reservations.removeIf(x -> !x.getClass().equals(HolidayHomeReservation.class) || ((HolidayHomeReservation) x).getHolidayHome().getId() != homeId);

        return reservations;
    }

    public Collection<Reservation> getAllForBoat(Long boatId) {
        var reservations = new ArrayList<Reservation>(reservationRepository.findAll());
        reservations.removeIf(x -> ((BoatReservation) x).getBoat().getId() != boatId);
        reservations.removeIf(x -> !x.getClass().equals(BoatReservation.class) || ((BoatReservation) x).getBoat().getId() != boatId);
        return reservations;
    }

    public void approveReservation(Long id) {
        Reservation reservation = findByID(id);
        reservation.setApproved(true);
        //availablePeriodService.occupyBy(reservation);
        addReservationToEntitiesRemoveOverlapping(reservation);
        emailService.sendSimpleMessage(loggedUserService.getUsername(), "Reservation accepted", "Your reservation for period: " + reservation.getStartDate().toString() + " - " + reservation.getEndDate().toString() + " has been accepted.");
    }

    private void addReservationToEntitiesRemoveOverlapping(Reservation newReservation) {
        if (newReservation.getClass().equals(HolidayHomeReservation.class)) {
            var newHomeReservation = (HolidayHomeReservation) newReservation;
            holidayHomeService.addReservationRemoveOverlapping(newHomeReservation);
        } else if (newReservation.getClass().equals(BoatReservation.class)) {
            var newBoatReservation = (BoatReservation) newReservation;
            boatService.addReservationRemoveOverlapping(newBoatReservation);
        } else if (newReservation.getClass().equals(AdventureReservation.class)) {
            var newAdventureReservation = (AdventureReservation) newReservation;
            adventureService.addReservationRemoveOverlapping(newAdventureReservation);
        }
    }
}
