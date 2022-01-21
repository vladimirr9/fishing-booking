package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.service.SubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/subscribe")
public class SubscribeController {
    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }
    @PostMapping(value = "/boat/{userEmail}/{boatId}")
    public ResponseEntity subscribeBoat(@PathVariable String userEmail,@PathVariable Long boatId){
        this.subscribeService.subscribeBoat(userEmail,boatId);
       return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/boat/{userEmail}/{houseId}")
    public ResponseEntity subscribeHome(@PathVariable String userEmail,@PathVariable Long houseId){
        this.subscribeService.subscribeHome(userEmail,houseId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/boat/{userEmail}/{adventureId}")
    public ResponseEntity subscribeAdventure(@PathVariable String userEmail,@PathVariable Long adventureId){
        this.subscribeService.subscribeAdventure(userEmail,adventureId);
        return ResponseEntity.noContent().build();
    }
}
