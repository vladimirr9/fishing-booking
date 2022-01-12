package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.NewBoatDTO;
import com.project.fishingbookingback.dto.request.NewComplaintDTO;
import com.project.fishingbookingback.dto.response.ClientsHolidayHomeDTO;
import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.Complaint;
import com.project.fishingbookingback.model.HolidayHome;
import com.project.fishingbookingback.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/complaint")
public class ComplaintController {
    private ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping()
    public ResponseEntity<Complaint> create(@Valid @RequestBody NewComplaintDTO dto) {
        Complaint complaint = complaintService.addComplaint(dto);
        return (ResponseEntity<Complaint>) ResponseEntity.ok(complaint);
    }

}
