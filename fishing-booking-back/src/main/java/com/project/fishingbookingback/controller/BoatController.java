package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.BoatMapper;
import com.project.fishingbookingback.dto.request.NewBoatDTO;
import com.project.fishingbookingback.dto.response.ClientsBoatViewDTO;
import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.HolidayHome;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.service.BoatService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/boat")
public class BoatController {
    private final BoatService boatService;
    private final BoatMapper boatMapper;

    public BoatController(BoatService boatService, BoatMapper boatMapper) {
        this.boatService = boatService;
        this.boatMapper = boatMapper;
    }

    @GetMapping()
    public ResponseEntity<List<Boat>> getBoatsForOwner(@RequestParam(required = false) String ownerUsername,
                                                             @RequestParam(required = false) String search) {;
        return ResponseEntity.ok(boatService.getBoatsForOwner(ownerUsername, search));
    }

    @GetMapping(value="/client")
    public ResponseEntity<List<ClientsBoatViewDTO>> getBoats(){
        List<ClientsBoatViewDTO> boatViewDTOs = new ArrayList<>();
        for(Boat boat: boatService.getAll())
            boatViewDTOs.add(boatMapper.toBoatViewDTO(boat));

        return ResponseEntity.ok(boatViewDTOs);
    }

    @GetMapping(value="/client/freeBoats")
    public ResponseEntity<List<ClientsBoatViewDTO>> getAvailableBoats(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime to){
        List<ClientsBoatViewDTO> boatViewDTOs = new ArrayList<>();
        for(Boat boat: boatService.getAvailableBoats(from,to))
            boatViewDTOs.add(boatMapper.toBoatViewDTO(boat));

        return ResponseEntity.ok(boatViewDTOs);
    }

    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @PostMapping()
    public ResponseEntity<Boat> create(@Valid @RequestBody NewBoatDTO dto) {
        Boat boat = boatMapper.toModel(dto);
        Boat newBoat = boatService.create(boat);
        return (ResponseEntity<Boat>) ResponseEntity.ok(newBoat);
    }
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @PostMapping(value = "/{id}/pictures/{is_interior}")
    public ResponseEntity<Boat> addPicture(@Valid @RequestBody Picture picture, @PathVariable Long id, @PathVariable Boolean is_interior) {
        return ResponseEntity.ok(boatService.addPicture(id, is_interior, picture));
    }
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Boat> update(@Valid @RequestBody NewBoatDTO dto, @PathVariable Long id) {
        Boat newBoat = boatService.update(id, dto);
        return (ResponseEntity<Boat>) ResponseEntity.ok(newBoat);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Boat> getBoat(@PathVariable Long id) {
        return ResponseEntity.ok(boatService.findByID(id));
    }
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteBoat(@PathVariable Long id) {
        boatService.deleteBoat(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @DeleteMapping(value = "/{id}/pictures/{is_interior}/{id_picture}")
    public ResponseEntity<HttpStatus> deletePicture(@PathVariable Long id, @PathVariable Long id_picture, @PathVariable Boolean is_interior) {
        boatService.deletePicture(id, id_picture, is_interior);
        return ResponseEntity.noContent().build();
    }
}
