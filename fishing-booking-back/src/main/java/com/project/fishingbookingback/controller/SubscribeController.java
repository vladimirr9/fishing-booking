package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.service.SubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/subscribe")
public class SubscribeController {
    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @GetMapping(value = "/boat/{userEmail}/{boatId}")
    public ResponseEntity<Boolean> isBoatSubscribed(@PathVariable String userEmail,@PathVariable Long boatId){
        return ResponseEntity.ok((subscribeService.isBoatSubscribed(userEmail,boatId)));
    }

    @GetMapping(value = "/home/{userEmail}/{homeId}")
    public ResponseEntity<Boolean> isHomeSubscribed(@PathVariable String userEmail,@PathVariable Long homeId){
        return ResponseEntity.ok((subscribeService.isHomeSubscribed(userEmail,homeId)));
    }

    @GetMapping(value = "/adventure/{userEmail}/{adventureId}")
    public ResponseEntity<Boolean> isAdventureSubscribed(@PathVariable String userEmail,@PathVariable Long adventureId){
        return ResponseEntity.ok((subscribeService.isAdventureSubscribed(userEmail,adventureId)));
    }


    @PostMapping(value = "/boat/{userEmail}/{boatId}")
    public ResponseEntity subscribeBoat(@PathVariable String userEmail,@PathVariable Long boatId){
        this.subscribeService.subscribeBoat(userEmail,boatId);
       return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/home/{userEmail}/{houseId}")
    public ResponseEntity subscribeHome(@PathVariable String userEmail,@PathVariable Long houseId){
        this.subscribeService.subscribeHome(userEmail,houseId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/adventure/{userEmail}/{adventureId}")
    public ResponseEntity subscribeAdventure(@PathVariable String userEmail,@PathVariable Long adventureId){
        this.subscribeService.subscribeAdventure(userEmail,adventureId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/boat/{userEmail}/{boatId}")
    public ResponseEntity unsubscribeBoat(@PathVariable String userEmail,@PathVariable Long boatId){
        this.subscribeService.unsubscribeBoat(userEmail,boatId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/home/{userEmail}/{homeId}")
    public ResponseEntity unsubscribeHome(@PathVariable String userEmail,@PathVariable Long homeId){
        this.subscribeService.unsubscribeHome(userEmail,homeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/adventure/{userEmail}/{adventureId}")
    public ResponseEntity unsubscribeAdventure(@PathVariable String userEmail,@PathVariable Long adventureId){
        this.subscribeService.unsubscribeAdventure(userEmail,adventureId);
        return ResponseEntity.noContent().build();
    }
}
