package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.ComplaintAnswerDTO;
import com.project.fishingbookingback.model.Complaint;
import com.project.fishingbookingback.service.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(value = "/create/{id}")
    public ResponseEntity<HttpStatus> createComplaint(@PathVariable Long id, @RequestBody String content) {
        complaintService.createComplaint(id, content);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/answer")
    public ResponseEntity<HttpStatus> answerComplaint(@PathVariable Long id, @RequestBody @Valid ComplaintAnswerDTO complaintAnswerDTO) {
        complaintService.answerComplaint(id, complaintAnswerDTO.getMessage());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<Complaint>> getAll() {
        return ResponseEntity.ok(complaintService.findAll());
    }
}
