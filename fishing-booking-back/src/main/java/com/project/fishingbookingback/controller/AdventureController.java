package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.service.AdventureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/fishing-adventure")
public class AdventureController {
    private AdventureService adventureService;

    public AdventureController(AdventureService adventureService) {
        this.adventureService = adventureService;
    }

    @PostMapping()
    public ResponseEntity<FishingAdventure> create(@Valid @RequestBody FishingAdventure fishingAdventure) {
        FishingAdventure newFishingAdventure = adventureService.create(fishingAdventure);
        return ResponseEntity.ok(newFishingAdventure);
    }
}
