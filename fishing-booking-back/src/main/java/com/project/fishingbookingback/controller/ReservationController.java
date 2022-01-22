package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.ReportMapper;
import com.project.fishingbookingback.dto.mapper.ReservationMapper;
import com.project.fishingbookingback.dto.request.CreateReservationDTO;
import com.project.fishingbookingback.dto.request.ReportAnswerRequestDTO;
import com.project.fishingbookingback.dto.request.ReportRequestDTO;
import com.project.fishingbookingback.dto.response.ReservationDTO;
import com.project.fishingbookingback.model.Report;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.service.PromotionService;
import com.project.fishingbookingback.service.ReservationCancelationService;
import com.project.fishingbookingback.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final ReservationCancelationService reservationCancelationService;
    private final PromotionService promotionService;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper, ReportMapper reportMapper, ReservationCancelationService reservationCancelationService, PromotionService promotionService) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
        this.reportMapper = reportMapper;
        this.reservationCancelationService = reservationCancelationService;
        this.promotionService = promotionService;
    }

    @GetMapping
    @Transactional
    public List<ReservationDTO> getAll(@RequestParam(required = false) String ownerEmail) {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservationService.getAll(ownerEmail)) {
            reservationDTOS.add(reservationMapper.toDTO(reservation));
        }
        return reservationDTOS;
    }

    @PutMapping(value = "/{id}/reports")
    public ResponseEntity<Report> addReport(@Valid @RequestBody ReportRequestDTO reportRequestDTO, @PathVariable Long id) {
        Report report = reportMapper.toModel(reportRequestDTO);
        Report newReport = reservationService.addReport(id, report);
        return ResponseEntity.ok(newReport);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long id) {
        reservationCancelationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reports/{report_id}/approve")
    public ResponseEntity<HttpStatus> approveReport(@PathVariable Long id, @PathVariable Long report_id, @RequestBody ReportAnswerRequestDTO reportAnswerRequestDTO) {
        reservationService.approveReport(id, report_id, reportAnswerRequestDTO.getMessage());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reports/{report_id}/deny")
    public ResponseEntity<HttpStatus> denyReport(@PathVariable Long id, @PathVariable Long report_id, @RequestBody ReportAnswerRequestDTO reportAnswerRequestDTO) {
        reservationService.denyReport(id, report_id, reportAnswerRequestDTO.getMessage());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody CreateReservationDTO createReservationDTO) {
        reservationService.createReservation(createReservationDTO.getPrice(), createReservationDTO.getFrom(), createReservationDTO.getTo(), createReservationDTO.getClientUsername(), createReservationDTO.getEntityId(), createReservationDTO.getType(),createReservationDTO.getAdditionalServicesIds());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create/{promotion_id}")
    public ResponseEntity<HttpStatus> createWithPromotion(@Valid @RequestBody CreateReservationDTO createReservationDTO,@PathVariable Long promotion_id) {
        reservationService.createReservation(createReservationDTO.getPrice(), createReservationDTO.getFrom(), createReservationDTO.getTo(), createReservationDTO.getClientUsername(), createReservationDTO.getEntityId(), createReservationDTO.getType(), createReservationDTO.getAdditionalServicesIds());
        promotionService.removePromotion(promotion_id,createReservationDTO.getType());
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{id}/approve")
    public ResponseEntity<HttpStatus> approveReservation(@PathVariable Long id) {
        reservationService.approveReservation(id);
        return ResponseEntity.noContent().build();
    }

}
