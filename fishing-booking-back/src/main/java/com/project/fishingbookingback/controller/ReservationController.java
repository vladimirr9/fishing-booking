package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.ReservationMapper;
import com.project.fishingbookingback.dto.response.ReservationDTO;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.service.ReservationService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    @Transactional
    public List<ReservationDTO> getAll(){
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation: reservationService.getAll())
            reservationDTOS.add(reservationMapper.toDTO(reservation));

        return reservationDTOS;
    }
}
