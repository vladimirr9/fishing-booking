package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.service.SubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/subscribe")
public class SubscribeController {
    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }


    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/boat/{userEmail}/{boatId}")
    public ResponseEntity<Boolean> isBoatSubscribed(@PathVariable String userEmail, @PathVariable Long boatId) {
        return ResponseEntity.ok((subscribeService.isBoatSubscribed(userEmail, boatId)));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/home/{userEmail}/{homeId}")
    public ResponseEntity<Boolean> isHomeSubscribed(@PathVariable String userEmail, @PathVariable Long homeId) {
        return ResponseEntity.ok((subscribeService.isHomeSubscribed(userEmail, homeId)));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/adventure/{userEmail}/{adventureId}")
    public ResponseEntity<Boolean> isAdventureSubscribed(@PathVariable String userEmail, @PathVariable Long adventureId) {
        return ResponseEntity.ok((subscribeService.isAdventureSubscribed(userEmail, adventureId)));
    }


    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(value = "/boat/{userEmail}/{boatId}")
    public ResponseEntity subscribeBoat(@PathVariable String userEmail, @PathVariable Long boatId) {
        this.subscribeService.subscribeBoat(userEmail, boatId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(value = "/home/{userEmail}/{houseId}")
    public ResponseEntity subscribeHome(@PathVariable String userEmail, @PathVariable Long houseId) {
        this.subscribeService.subscribeHome(userEmail, houseId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(value = "/adventure/{userEmail}/{adventureId}")
    public ResponseEntity subscribeAdventure(@PathVariable String userEmail, @PathVariable Long adventureId) {
        this.subscribeService.subscribeAdventure(userEmail, adventureId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping(value = "/boat/{userEmail}/{boatId}")
    public ResponseEntity unsubscribeBoat(@PathVariable String userEmail, @PathVariable Long boatId) {
        this.subscribeService.unsubscribeBoat(userEmail, boatId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping(value = "/home/{userEmail}/{homeId}")
    public ResponseEntity unsubscribeHome(@PathVariable String userEmail, @PathVariable Long homeId) {
        this.subscribeService.unsubscribeHome(userEmail, homeId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping(value = "/adventure/{userEmail}/{adventureId}")
    public ResponseEntity unsubscribeAdventure(@PathVariable String userEmail, @PathVariable Long adventureId) {
        this.subscribeService.unsubscribeAdventure(userEmail, adventureId);
        return ResponseEntity.noContent().build();
    }
}
