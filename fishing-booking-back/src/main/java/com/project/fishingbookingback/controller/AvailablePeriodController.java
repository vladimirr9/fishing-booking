package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.AvailablePeriodDTO;
import com.project.fishingbookingback.exception.NewAvailablePeriodOverlapsException;
import com.project.fishingbookingback.model.AvailablePeriod;
import com.project.fishingbookingback.service.AvailablePeriodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/available-periods")
public class AvailablePeriodController {
    private final AvailablePeriodService availablePeriodService;

    public AvailablePeriodController(AvailablePeriodService availablePeriodService) {
        this.availablePeriodService = availablePeriodService;
    }

    @GetMapping
    public ResponseEntity<List<AvailablePeriod>> getPeriods(@RequestParam(required = false) String providerEmail) {
        return ResponseEntity.ok(availablePeriodService.getPeriods(providerEmail));
    }

    @PostMapping("/instructor")
    public ResponseEntity<AvailablePeriod> addPeriodForInstructor(@RequestBody @Valid AvailablePeriodDTO availablePeriodDTO) {
        AvailablePeriod availablePeriod = new AvailablePeriod(availablePeriodDTO.getFrom(), availablePeriodDTO.getTo());
        try {
            AvailablePeriod newAvailablePeriod = availablePeriodService.createForInstructor(availablePeriod, availablePeriodDTO.getEmail());
            return ResponseEntity.ok(newAvailablePeriod);
        } catch (NewAvailablePeriodOverlapsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }

    @PostMapping("/holidayHome/{homeId}")
    public ResponseEntity<AvailablePeriod> addPeriodForHolidayHome(@RequestBody @Valid AvailablePeriodDTO availablePeriodDTO, @PathVariable Long homeId) {
        AvailablePeriod availablePeriod = new AvailablePeriod(availablePeriodDTO.getFrom(), availablePeriodDTO.getTo());
        try {
            AvailablePeriod newAvailablePeriod = availablePeriodService.createForHolidayHome(availablePeriod, homeId, availablePeriodDTO.getEmail());
            return ResponseEntity.ok(newAvailablePeriod);
        } catch (NewAvailablePeriodOverlapsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/boat/{boatId}")
    public ResponseEntity<AvailablePeriod> addPeriodForBoat(@RequestBody @Valid AvailablePeriodDTO availablePeriodDTO, @PathVariable Long boatId) {
        AvailablePeriod availablePeriod = new AvailablePeriod(availablePeriodDTO.getFrom(), availablePeriodDTO.getTo());
        try {
            AvailablePeriod newAvailablePeriod = availablePeriodService.createForBoat(availablePeriod, boatId, availablePeriodDTO.getEmail());
            return ResponseEntity.ok(newAvailablePeriod);
        } catch (NewAvailablePeriodOverlapsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<HttpStatus> deletePeriod(@PathVariable Long id) {
        availablePeriodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
