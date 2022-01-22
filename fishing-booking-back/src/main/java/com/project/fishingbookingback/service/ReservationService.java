package com.project.fishingbookingback.service;

import com.project.fishingbookingback.dto.request.IncomeRequestDTO;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.exception.UnrecognizedTypeException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.ReservationRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
    private final AdditionalServiceService additionalServiceService;


    public ReservationService(ReservationRepository reservationRepository, ReportService reportService, EmailService emailService, HolidayHomeService holidayHomeService, UserService userService, BoatService boatService, AdventureService adventureService, @Lazy AvailablePeriodService availablePeriodService, LoggedUserService loggedUserService, AvailableAdventureService availableAdventureService, AdditionalServiceService additionalServiceService) {
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
        this.additionalServiceService = additionalServiceService;
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

    public void createReservation(double price, LocalDateTime from, LocalDateTime to, String clientUsername, Long entityId, String type, Long[] additionalServicesIds, boolean isPromotion) {
        if (from.isBefore(LocalDateTime.now())) throw new NotAllowedException();
        if (to.isBefore(from)) throw new NotAllowedException();
        Reservation reservation = createReservationdownClass(type, entityId, from, to, isPromotion);
        Client client = (Client) userService.findByEmail(clientUsername);
        reservation.setClient(client);
        reservation.setPrice(price);
        reservation.setStartDate(from);
        reservation.setEndDate(to);
        reservation.setApproved(false);
        setAdittionalServices(reservation, additionalServicesIds);
        reservationRepository.save(reservation);
        emailService.sendSimpleMessage(clientUsername, "Reservation", "Reservation request successfully sent!");
    }

    private void setAdittionalServices(Reservation reservation, Long[] additionalServicesIds) {
        if (additionalServicesIds == null) return;
        if (reservation.getAdditionalServices() == null)
            reservation.setAdditionalServices(new ArrayList<>());
        for (Long id : additionalServicesIds)
            reservation.getAdditionalServices().add(additionalServiceService.findById(id));

    }


    private Reservation createReservationdownClass(String type, Long entityId, LocalDateTime from, LocalDateTime to, boolean isPromotion) {
        switch (type) {
            case "ADVENTURE" -> {
                var adventureReservation = new AdventureReservation();
                FishingAdventure adventure = adventureService.findByID(entityId);
                if (!isPromotion)
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
                if (!isPromotion)
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
                if (!isPromotion)
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
        reservations.removeIf(x -> !x.getClass().equals(AdventureReservation.class) || !((AdventureReservation) x).getAdventure().getFishingInstructor().getEmail().equals(email));
        return reservations;
    }

    public Collection<Reservation> getAllForHome(Long homeId) {
        var reservations = new ArrayList<Reservation>(reservationRepository.findAll());
        reservations.removeIf(x -> !x.getClass().equals(HolidayHomeReservation.class) || ((HolidayHomeReservation) x).getHolidayHome().getId() != homeId);

        return reservations;
    }

    public Collection<Reservation> getAllForBoat(Long boatId) {
        var reservations = new ArrayList<Reservation>(reservationRepository.findAll());
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

    public void denyReview(Long id) {
        Reservation reservation = findByID(id);
        reservation.setReview(null);
        reservationRepository.save(reservation);

    }

    public void answerComplaint(Long id) {
        Reservation reservation = findByID(id);
        reservation.setComplaint(null);
        reservationRepository.save(reservation);
    }
    
    public boolean isAdventureOccupied(Long fishingAdventureId) {
        LocalDateTime now = LocalDateTime.now();
        for (Reservation reservation : reservationRepository.findAll()) {
            if (reservation.getClass() == AdventureReservation.class) {
                AdventureReservation ar = (AdventureReservation) reservation;
                if (Objects.equals(ar.getAdventure().getId(), fishingAdventureId) && reservation.overlaps(now))
                    return true;
            }
        }
        return false;
    }

    public boolean isHomeOccupied(Long holidayHomeId) {
        LocalDateTime now = LocalDateTime.now();
        for (Reservation reservation : reservationRepository.findAll()) {
            if (reservation.getClass() == HolidayHomeReservation.class) {
                HolidayHomeReservation ar = (HolidayHomeReservation) reservation;
                if (Objects.equals(ar.getHolidayHome().getId(), holidayHomeId) && reservation.overlaps(now))
                    return true;
            }
        }
        return false;
    }

    public boolean isBoatOccupied(Long boatId) {
        LocalDateTime now = LocalDateTime.now();
        for (Reservation reservation : reservationRepository.findAll()) {
            if (reservation.getClass() == BoatReservation.class) {
                BoatReservation ar = (BoatReservation) reservation;
                if (Objects.equals(ar.getBoat().getId(), boatId) && reservation.overlaps(now))
                    return true;
            }
        }
        return false;
    }

    public double income(IncomeRequestDTO dto) {
        double income = 0;
        for (Reservation reservation : getAll(dto.getEmail())) {
            if (reservation.getStartDate().isAfter(dto.getFrom()) && reservation.getEndDate().isBefore(dto.getTo())) {
                income += reservation.getPrice();
            }
        }
        return income;
    }

    public List<Reservation> getAllForClient(String clientEmail) {
        List<Reservation> reservations = reservationRepository.findAll();
        if (clientEmail != null)
            reservations.removeIf(n -> (!n.getClient().getEmail().equals(clientEmail)));

        return reservations;
    }
}
