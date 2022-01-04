package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.BoatMapper;
import com.project.fishingbookingback.dto.response.ClientsBoatViewDTO;
import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.service.BoatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value="/client")
    public ResponseEntity<List<ClientsBoatViewDTO>> getBoats(){
        List<ClientsBoatViewDTO> boatViewDTOs = new ArrayList<>();
        for(Boat boat: boatService.getAll())
            boatViewDTOs.add(boatMapper.toBoatViewDTO(boat));

        return ResponseEntity.ok(boatViewDTOs);
    }
}
