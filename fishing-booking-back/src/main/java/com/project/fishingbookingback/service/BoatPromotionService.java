package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.OverlapsException;
import com.project.fishingbookingback.model.AvailablePeriod;
import com.project.fishingbookingback.model.BoatPromotion;
import com.project.fishingbookingback.model.Client;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.BoatPromotionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BoatPromotionService {

    private final BoatPromotionRepository repository;
    private final AvailablePeriodService availablePeriodService;
    private final ReservationService reservationService;
    private final LoggedUserService loggedUserService;
    private final EmailService emailService;

    public BoatPromotionService(BoatPromotionRepository repository, @Lazy AvailablePeriodService availablePeriodService, @Lazy ReservationService reservationService, LoggedUserService loggedUserService, EmailService emailService) {
        this.repository = repository;
        this.availablePeriodService = availablePeriodService;
        this.reservationService = reservationService;
        this.loggedUserService = loggedUserService;
        this.emailService = emailService;
    }

    public BoatPromotion findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(BoatPromotion.class.getSimpleName()));
    }


    public BoatPromotion addPromotion(BoatPromotion boatPromotion) {
        Collection<AvailablePeriod> availablePeriods = availablePeriodService.getPeriods(boatPromotion.getBoat().getBoatOwner().getEmail()).stream().filter(x -> x.getBoat() != null && x.getBoat().getId().equals(boatPromotion.getBoat().getId())).toList();
        Collection<Reservation> reservations = reservationService.getAllForBoat(boatPromotion.getBoat().getId());
        Collection<BoatPromotion> promotions = repository.findByBoat_Id(boatPromotion.getBoat().getId());
        checkIfOverlaps(boatPromotion, availablePeriods, reservations, promotions);
        sendMail(boatPromotion.getBoat().getSubscribedClients(), boatPromotion.getBoat().getName());
        return repository.save(boatPromotion);
    }

    private void sendMail(List<Client> subscribedClients, String entityName) {
        for (var client: subscribedClients) {
            emailService.sendSimpleMessage(client.getEmail(), "New promotion is out!", "Do not miss out on new promotion for " + entityName);
        }
    }

    public void deletePromotion(Long id_promotion) {
        findByID(id_promotion);
        repository.deleteById(id_promotion);
    }

    public List<BoatPromotion> getPromotions(Long id) {
        return repository.findByBoat_Id(id);
    }
    public Collection<BoatPromotion> getAllForBoatOwner(String email) { return  repository.getAllForBoatOwner(email);}

    private void checkIfOverlaps(BoatPromotion boatPromotion, Collection<AvailablePeriod> availablePeriods, Collection<Reservation> reservations, Collection<BoatPromotion> promotions) {
        for (var availablePeriod: availablePeriods) {
            if (boatPromotion.overlaps(availablePeriod)) {
                throw new OverlapsException(availablePeriod.getClass().getSimpleName(), availablePeriod.getId());
            }
        }
        for (var reservation: reservations) {
            if (reservation.isApproved() && boatPromotion.overlaps(reservation)) {
                throw new OverlapsException(reservation.getClass().getSimpleName(), reservation.getId());
            }
        }
        for (var promotion: promotions) {
            if (boatPromotion.overlaps(promotion)) {
                throw new OverlapsException(promotion.getClass().getSimpleName(), promotion.getId());
            }
        }
    }







}
