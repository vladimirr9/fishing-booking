package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.ProviderRegistrationDeniedRequestDTO;
import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.Role;
import com.project.fishingbookingback.service.ProviderRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/provider-registration")
public class ProviderRegistrationController {
    private final ProviderRegistrationService providerRegistrationService;

    public ProviderRegistrationController(ProviderRegistrationService providerRegistrationService) {
        this.providerRegistrationService = providerRegistrationService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProviderRegistration> getOne(@PathVariable Long id) {
        ProviderRegistration found = providerRegistrationService.findById(id);
        return ResponseEntity.ok(found);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<ProviderRegistration>> getAll(@RequestParam(required = false) Role exclude) {
        List<ProviderRegistration> found = providerRegistrationService.findAll(exclude);
        return ResponseEntity.ok(found);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/approve")
    public ResponseEntity<HttpStatus> approve(@PathVariable Long id) {
        providerRegistrationService.approve(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/deny")
    public ResponseEntity<HttpStatus> deny(@PathVariable Long id, @Valid @RequestBody ProviderRegistrationDeniedRequestDTO providerRegistrationDeniedRequestDTO) {
        providerRegistrationService.deny(id, providerRegistrationDeniedRequestDTO.getMessage());
        return ResponseEntity.noContent().build();
    }
}
