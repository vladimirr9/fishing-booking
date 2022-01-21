package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.AdventureMapper;
import com.project.fishingbookingback.dto.mapper.PromotionMapper;
import com.project.fishingbookingback.dto.request.FishingPromotionDTO;
import com.project.fishingbookingback.dto.request.NewAdventureDTO;
import com.project.fishingbookingback.dto.request.UpdateAdventureDTO;
import com.project.fishingbookingback.dto.response.ClientsAdventureViewDTO;
import com.project.fishingbookingback.model.AdditionalService;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.FishingPromotion;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.service.AdventureService;
import com.project.fishingbookingback.service.AvailableAdventureService;
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
@RequestMapping(value = "/api/fishing-adventures")
public class AdventureController {
    private AdventureService adventureService;
    private AdventureMapper adventureMapper;
    private final AvailableAdventureService availableAdventureService;
    private final PromotionMapper promotionMapper;

    public AdventureController(AdventureService adventureService, AdventureMapper adventureMapper, AvailableAdventureService availableAdventureService, PromotionMapper promotionMapper) {
        this.adventureService = adventureService;
        this.adventureMapper = adventureMapper;
        this.availableAdventureService = availableAdventureService;
        this.promotionMapper = promotionMapper;
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @PostMapping()
    public ResponseEntity<FishingAdventure> create(@Valid @RequestBody NewAdventureDTO newAdventureDTO) {
        FishingAdventure fishingAdventure = adventureMapper.toModel(newAdventureDTO);
        FishingAdventure newFishingAdventure = adventureService.create(fishingAdventure);
        return ResponseEntity.ok(newFishingAdventure);
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @PostMapping(value = "/{id}/additional-services")
    public ResponseEntity<FishingAdventure> addService(@Valid @RequestBody AdditionalService additionalService, @PathVariable Long id) {
        return ResponseEntity.ok(adventureService.addService(id, additionalService));
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @PostMapping(value = "/{id}/pictures")
    public ResponseEntity<FishingAdventure> addPicture(@Valid @RequestBody Picture picture, @PathVariable Long id) {
        return ResponseEntity.ok(adventureService.addPicture(id, picture));
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<FishingAdventure> updateAdventure(@Valid @RequestBody UpdateAdventureDTO updateAdventureDTO, @PathVariable Long id) {
        FishingAdventure fishingAdventure = adventureMapper.toModel(updateAdventureDTO);
        return ResponseEntity.ok(adventureService.updateAdventure(id, fishingAdventure));
    }


    @GetMapping()
    public ResponseEntity<List<FishingAdventure>> getAdventures(@RequestParam(required = false) String instructorUsername,
                                                                @RequestParam(required = false) String adventureName) {
        return ResponseEntity.ok(adventureService.getAdventures(instructorUsername, adventureName));
    }

    @Transactional
    @GetMapping(value = "/client")
    public ResponseEntity<List<ClientsAdventureViewDTO>> getClientAdventures() {
        List<ClientsAdventureViewDTO> clientsAdventureViewDTOS = new ArrayList<>();
        for (FishingAdventure adventure : adventureService.getAllAdventures())
            clientsAdventureViewDTOS.add(adventureMapper.toClientAdventureDTO(adventure));

        return ResponseEntity.ok(clientsAdventureViewDTOS);
    }

    @GetMapping(value = "/client/freePeriods")
    public ResponseEntity<List<ClientsAdventureViewDTO>> getAvailableAdventures(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<ClientsAdventureViewDTO> clientsAdventureViewDTOS = new ArrayList<>();
        for (FishingAdventure adventure : availableAdventureService.getAvailableAdventures(from, to))
            clientsAdventureViewDTOS.add(adventureMapper.toClientAdventureDTO(adventure));
        return ResponseEntity.ok(clientsAdventureViewDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FishingAdventure> getAdventure(@PathVariable Long id) {
        return ResponseEntity.ok(adventureService.findByID(id));
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteAdventure(@PathVariable Long id) {
        adventureService.deleteAdventure(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}/additional-services/{id_service}")
    public ResponseEntity<HttpStatus> deleteAdditionalService(@PathVariable Long id, @PathVariable Long id_service) {
        adventureService.deleteAdditionalService(id, id_service);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}/pictures/{id_picture}")
    public ResponseEntity<HttpStatus> deletePicture(@PathVariable Long id, @PathVariable Long id_picture) {
        adventureService.deletePicture(id, id_picture);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/promotions")
    public ResponseEntity<FishingPromotion> addPromotion(@Valid @RequestBody FishingPromotionDTO promotionDTO, @PathVariable Long id) {
        FishingPromotion fishingPromotion = promotionMapper.toModel(promotionDTO);
        return ResponseEntity.ok(adventureService.addPromotion(id, fishingPromotion));
    }

    @DeleteMapping(value = "/{id}/promotions/{id_promotion}")
    public ResponseEntity<HttpStatus> deletePromotion(@PathVariable Long id, @PathVariable Long id_promotion) {
        adventureService.deletePromotion(id, id_promotion);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/promotions")
    public ResponseEntity<Collection<FishingPromotion>> getAllPromotions() {
        return ResponseEntity.ok(adventureService.getAllPromotions());
    }

    @GetMapping(value = "/{id}/promotions")
    public ResponseEntity<List<FishingPromotion>> getPromotions(@PathVariable Long id) {
        return ResponseEntity.ok(adventureService.getPromotions(id));
    }


}
