package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.HolidayHomeMapper;
import com.project.fishingbookingback.dto.request.NewHolidayHomeDTO;
import com.project.fishingbookingback.dto.response.ClientsHolidayHomeDTO;
import com.project.fishingbookingback.dto.response.HomeResponseDTO;
import com.project.fishingbookingback.exception.EntityOccupiedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.service.HolidayHomeService;
import com.project.fishingbookingback.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/holiday-home")
public class HolidayHomeController {
    private HolidayHomeService holidayHomeService;
    private HolidayHomeMapper holidayHomeMapper;
    private ReservationService reservationService;

    public HolidayHomeController(HolidayHomeService holidayHomeService, HolidayHomeMapper holidayHomeMapper, ReservationService reservationService) {
        this.holidayHomeService = holidayHomeService;
        this.holidayHomeMapper = holidayHomeMapper;
        this.reservationService = reservationService;
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @PostMapping()
    public ResponseEntity<HolidayHome> create(@Valid @RequestBody NewHolidayHomeDTO newHolidayHomeDTO) {
        HolidayHome holidayHome = holidayHomeMapper.toModel(newHolidayHomeDTO);
        HolidayHome newHolidayHome = holidayHomeService.create(holidayHome);
        return (ResponseEntity<HolidayHome>) ResponseEntity.ok(newHolidayHome);
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @PostMapping(value = "/{id}/pictures/{is_interior}")
    public ResponseEntity<HolidayHome> addPicture(@Valid @RequestBody Picture picture, @PathVariable Long id, @PathVariable Boolean is_interior) {
        return ResponseEntity.ok(holidayHomeService.addPicture(id, is_interior, picture));
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<HolidayHome> update(@Valid @RequestBody NewHolidayHomeDTO updatedHolidayHomeDTO, @PathVariable Long id) {
        if (reservationService.isHomeOccupied(id)) throw new EntityOccupiedException();
        HolidayHome newHolidayHome = holidayHomeService.update(id, updatedHolidayHomeDTO);
        return (ResponseEntity<HolidayHome>) ResponseEntity.ok(newHolidayHome);
    }

    @GetMapping()
    public ResponseEntity<List<HomeResponseDTO>> getHolidayHomes(@RequestParam(required = false) String homeOwnerUsername,
                                                                 @RequestParam(required = false) String holidayHomeName) {
        List<HomeResponseDTO> homeResponseDTOS = new ArrayList<>();
        List<HolidayHome> holidayHomes = holidayHomeService.getHolidayHomes(homeOwnerUsername, holidayHomeName);
        for (HolidayHome home : holidayHomes) {
            homeResponseDTOS.add(holidayHomeMapper.toHomeResponseDTO(home, reservationService.isHomeOccupied(home.getId())));
        }
        return ResponseEntity.ok(homeResponseDTOS);
    }

    @Transactional
    @GetMapping(value = "/client")
    public ResponseEntity<List<ClientsHolidayHomeDTO>> getAllHolidayHomes() {
        List<ClientsHolidayHomeDTO> clientsHolidayHomeDTOs = new ArrayList<>();
        for (HolidayHome home : holidayHomeService.getAll())
            clientsHolidayHomeDTOs.add(holidayHomeMapper.toHolidayViewDTO(home));

        return ResponseEntity.ok(clientsHolidayHomeDTOs);
    }

    @GetMapping(value = "/client/freeHomes")
    public ResponseEntity<List<ClientsHolidayHomeDTO>> getAvailableHomes(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                         @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<ClientsHolidayHomeDTO> clientsHolidayHomeDTOs = new ArrayList<>();
        for (HolidayHome home : holidayHomeService.getAvailableHomes(from, to))
            clientsHolidayHomeDTOs.add(holidayHomeMapper.toHolidayViewDTO(home));

        return ResponseEntity.ok(clientsHolidayHomeDTOs);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<HolidayHome> getHome(@PathVariable Long id) {
        return ResponseEntity.ok(holidayHomeService.findByID(id));
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteHolidayHome(@PathVariable Long id) {
        if (reservationService.isHomeOccupied(id)) throw new EntityOccupiedException();
        holidayHomeService.deleteHolidayHome(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @DeleteMapping(value = "/{id}/pictures/{is_interior}/{id_picture}")
    public ResponseEntity<HttpStatus> deletePicture(@PathVariable Long id, @PathVariable Long id_picture, @PathVariable Boolean is_interior) {
        holidayHomeService.deletePicture(id, id_picture, is_interior);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @PostMapping(value = "/{id}/promotions")
    public ResponseEntity<HolidayHomePromotion> addPromotion(@Valid @RequestBody Promotion promotion, @PathVariable Long id) {
        return ResponseEntity.ok(holidayHomeService.addPromotion(id, promotion));
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @DeleteMapping(value = "/{id}/promotions/{id_promotion}")
    public ResponseEntity<HttpStatus> deletePromotion(@PathVariable Long id, @PathVariable Long id_promotion) {
        holidayHomeService.deletePromotion(id, id_promotion);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/promotions")
    public ResponseEntity<List<HolidayHomePromotion>> getPromotions(@PathVariable Long id) {
        return ResponseEntity.ok(holidayHomeService.getPromotions(id));
    }

    @GetMapping(value = "/promotions")
    public ResponseEntity<Collection<HolidayHomePromotion>> getAllPromotions() {
        return ResponseEntity.ok(holidayHomeService.getAllPromotions());
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER')")
    @PostMapping(value = "/{id}/additional-services")
    public ResponseEntity<HolidayHome> addService(@Valid @RequestBody AdditionalService additionalService, @PathVariable Long id) {
        return ResponseEntity.ok(holidayHomeService.addService(id, additionalService));
    }

    @PreAuthorize("hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}/additional-services/{id_service}")
    public ResponseEntity<HttpStatus> deleteAdditionalService(@PathVariable Long id, @PathVariable Long id_service) {
        holidayHomeService.deleteAdditionalService(id, id_service);
        return ResponseEntity.noContent().build();
    }

}

