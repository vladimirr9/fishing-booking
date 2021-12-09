package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.HolidayHomeMapper;
import com.project.fishingbookingback.dto.request.NewHolidayHomeDTO;
import com.project.fishingbookingback.model.AdditionalService;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.HolidayHome;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.service.HolidayHomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/holiday-home")
public class HolidayHomeController {
    private HolidayHomeService holidayHomeService;
    private HolidayHomeMapper holidayHomeMapper;

    public HolidayHomeController(HolidayHomeService holidayHomeService, HolidayHomeMapper holidayHomeMapper) {
        this.holidayHomeService = holidayHomeService;
        this.holidayHomeMapper = holidayHomeMapper;
    }

    @PostMapping()
    public ResponseEntity<HolidayHome> create(@Valid @RequestBody NewHolidayHomeDTO newHolidayHomeDTO) {
        HolidayHome holidayHome = holidayHomeMapper.toModel(newHolidayHomeDTO);
        HolidayHome newHolidayHome = holidayHomeService.create(holidayHome);
        return (ResponseEntity<HolidayHome>) ResponseEntity.ok(newHolidayHome);
    }

    @PutMapping()
    public ResponseEntity<HolidayHome> update(@NotNull long id, @Valid NewHolidayHomeDTO updatedHolidayHomeDTO) {
        HolidayHome newHolidayHome = holidayHomeService.update(id, updatedHolidayHomeDTO);
        return (ResponseEntity<HolidayHome>) ResponseEntity.ok(newHolidayHome);
    }

    @GetMapping()
    public ResponseEntity<List<HolidayHome>> getHolidayHomes(@RequestParam(required = false) String homeOwnerUsername) {;
        return ResponseEntity.ok(holidayHomeService.getHolidayHomes(homeOwnerUsername));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteHolidayHome(@PathVariable Long id) {
        holidayHomeService.deleteHolidayHome(id);
        return ResponseEntity.noContent().build();
    }


}

