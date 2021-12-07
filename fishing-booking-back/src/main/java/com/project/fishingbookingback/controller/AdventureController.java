package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.AdventureMapper;
import com.project.fishingbookingback.dto.request.NewAdventureDTO;
import com.project.fishingbookingback.model.AdditionalService;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.service.AdventureService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fishing-adventures")
public class AdventureController {
    private AdventureService adventureService;
    private AdventureMapper adventureMapper;

    public AdventureController(AdventureService adventureService, AdventureMapper adventureMapper) {
        this.adventureService = adventureService;
        this.adventureMapper = adventureMapper;
    }

    @PostMapping()
    public ResponseEntity<FishingAdventure> create(@Valid @RequestBody NewAdventureDTO newAdventureDTO) {
        FishingAdventure fishingAdventure = adventureMapper.toModel(newAdventureDTO);
        FishingAdventure newFishingAdventure = adventureService.create(fishingAdventure);
        return ResponseEntity.ok(newFishingAdventure);
    }

    @PostMapping(value = "/{id}/additional-services")
    public ResponseEntity<FishingAdventure> addService(@Valid @RequestBody AdditionalService additionalService, @PathVariable Long id) {
        return ResponseEntity.ok(adventureService.addService(id, additionalService));
    }

    @PostMapping(value = "/{id}/pictures")
    public ResponseEntity<FishingAdventure> addPicture(@Valid @RequestBody Picture picture, @PathVariable Long id) {
        return ResponseEntity.ok(adventureService.addPicture(id, picture));
    }

    @GetMapping()
    public ResponseEntity<List<FishingAdventure>> getAdventures(@RequestParam(required = false) String instructorUsername) {
        return ResponseEntity.ok(adventureService.getAdventures(instructorUsername));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteAdventure(@PathVariable Long id) {
        adventureService.deleteAdventure(id);
        return ResponseEntity.noContent().build();
    }
}
