package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.ReportMapper;
import com.project.fishingbookingback.dto.mapper.ReservationMapper;
import com.project.fishingbookingback.dto.request.ReportRequestDTO;
import com.project.fishingbookingback.dto.response.ReservationDTO;
import com.project.fishingbookingback.model.Report;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final ReportMapper reportMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper, ReportMapper reportMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
        this.reportMapper = reportMapper;
    }

    @GetMapping
    @Transactional
    public List<ReservationDTO> getAll() {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservationService.getAll())
            reservationDTOS.add(reservationMapper.toDTO(reservation));

        return reservationDTOS;
    }

    @PutMapping(value = "/{id}/reports")
    public ResponseEntity<Report> addReport(@Valid @RequestBody ReportRequestDTO reportRequestDTO, @PathVariable Long id) {
        Report report = reportMapper.toModel(reportRequestDTO);
        Report newReport = reservationService.addReport(id, report);
        return ResponseEntity.ok(newReport);
    }

}
