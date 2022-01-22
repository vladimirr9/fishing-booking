package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.AvailablePeriodDTO;
import com.project.fishingbookingback.dto.response.AvailablePeriodCalendarDTO;
import com.project.fishingbookingback.model.AvailablePeriod;
import com.project.fishingbookingback.service.AvailablePeriodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/available-periods")
public class AvailablePeriodController {
    private final AvailablePeriodService availablePeriodService;

    public AvailablePeriodController(AvailablePeriodService availablePeriodService) {
        this.availablePeriodService = availablePeriodService;
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<AvailablePeriod>> getPeriods(@RequestParam(required = false) String providerEmail) {
        return ResponseEntity.ok(availablePeriodService.getPeriods(providerEmail));
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    @GetMapping("/calendar")
    public ResponseEntity<List<AvailablePeriodCalendarDTO>> getPeriodsCalendar(@RequestParam(required = false) String providerEmail) {
        return ResponseEntity.ok(availablePeriodService.getPeriods(providerEmail).stream().map(x -> new AvailablePeriodCalendarDTO(x)).toList());
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @PostMapping("/instructor")
    public ResponseEntity<AvailablePeriod> addPeriodForInstructor(@RequestBody @Valid AvailablePeriodDTO availablePeriodDTO) {
        AvailablePeriod availablePeriod = new AvailablePeriod(availablePeriodDTO.getFrom(), availablePeriodDTO.getTo());
        AvailablePeriod newAvailablePeriod = availablePeriodService.createForInstructor(availablePeriod, availablePeriodDTO.getEmail());
        return ResponseEntity.ok(newAvailablePeriod);

    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @PostMapping("/holidayHome/{homeId}")
    public ResponseEntity<AvailablePeriod> addPeriodForHolidayHome(@RequestBody @Valid AvailablePeriodDTO availablePeriodDTO, @PathVariable Long homeId) {
        AvailablePeriod availablePeriod = new AvailablePeriod(availablePeriodDTO.getFrom(), availablePeriodDTO.getTo());
        AvailablePeriod newAvailablePeriod = availablePeriodService.createForHolidayHome(availablePeriod, homeId, availablePeriodDTO.getEmail());
        return ResponseEntity.ok(newAvailablePeriod);

    }

    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @PostMapping("/boat/{boatId}")
    public ResponseEntity<AvailablePeriod> addPeriodForBoat(@RequestBody @Valid AvailablePeriodDTO availablePeriodDTO, @PathVariable Long boatId) {
        AvailablePeriod availablePeriod = new AvailablePeriod(availablePeriodDTO.getFrom(), availablePeriodDTO.getTo());
        AvailablePeriod newAvailablePeriod = availablePeriodService.createForBoat(availablePeriod, boatId, availablePeriodDTO.getEmail());
        return ResponseEntity.ok(newAvailablePeriod);
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<HttpStatus> deletePeriod(@PathVariable Long id) {
        availablePeriodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
