package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.service.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(value="/create/{id}")
    public ResponseEntity<HttpStatus> createComplaint(@PathVariable Long id, @RequestBody String content){
        complaintService.createComplaint(id,content);
        return ResponseEntity.noContent().build();
    }
}
