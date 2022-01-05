package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.AdventureReservationRepository;
import com.project.fishingbookingback.repository.BoatReservationRepository;
import com.project.fishingbookingback.repository.HolidayHomeReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final AdventureReservationRepository adventureReservationRepository;
    private final HolidayHomeReservationRepository holidayHomeReservationRepository;
    private final BoatReservationRepository boatReservationRepository;

    public ReservationService(AdventureReservationRepository adventureReservationRepository, HolidayHomeReservationRepository holidayHomeReservationRepository, BoatReservationRepository boatReservationRepository) {
        this.adventureReservationRepository = adventureReservationRepository;
        this.holidayHomeReservationRepository = holidayHomeReservationRepository;
        this.boatReservationRepository = boatReservationRepository;
    }

    public List<Reservation> getAll(){
        List<Reservation> reservations = new ArrayList<>();
        reservations.addAll(adventureReservationRepository.findAll());
        reservations.addAll(holidayHomeReservationRepository.findAll());
        reservations.addAll(boatReservationRepository.findAll());
        return  reservations;
    }
}
