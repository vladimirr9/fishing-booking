package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.model.ServiceFee;
import com.project.fishingbookingback.service.FeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/service-fees")
public class ServiceFeeController {

    private final FeeService feeService;

    public ServiceFeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping()
    public ResponseEntity<ServiceFee> get() {
        ServiceFee found = feeService.get();
        return ResponseEntity.ok(found);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping()
    public ResponseEntity<ServiceFee> update(@RequestBody @Valid ServiceFee serviceFee) {
        ServiceFee updated = feeService.update(serviceFee);
        return ResponseEntity.ok(updated);
    }
}
