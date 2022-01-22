package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.request.DeletionAnswerDTO;
import com.project.fishingbookingback.model.AccountDeletion;
import com.project.fishingbookingback.service.AccountDeletionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/account-deletions")
public class AccountDeletionController {
    private final AccountDeletionService accountDeletionService;

    public AccountDeletionController(AccountDeletionService accountDeletionService) {
        this.accountDeletionService = accountDeletionService;
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT')")
    @PostMapping()
    public ResponseEntity<AccountDeletion> create(@Valid @RequestBody AccountDeletion accountDeletion) {
        AccountDeletion newAccountDeletion = accountDeletionService.create(accountDeletion);
        return ResponseEntity.ok(newAccountDeletion);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<AccountDeletion>> getAll() {
        List<AccountDeletion> accountDeletions = accountDeletionService.getAll();
        return ResponseEntity.ok(accountDeletions);
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/{email}")
    public ResponseEntity<AccountDeletion> getOne(@PathVariable String email) {
        AccountDeletion accountDeletion = accountDeletionService.findOne(email);
        return ResponseEntity.ok(accountDeletion);
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT')")
    @DeleteMapping(value = "/{email}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String email) {
        accountDeletionService.delete(email);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/approve")
    public ResponseEntity<HttpStatus> approve(@PathVariable Long id, @Valid @RequestBody DeletionAnswerDTO deletionAnswerDTO) {
        accountDeletionService.approve(id, deletionAnswerDTO.getReason());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/deny")
    public ResponseEntity<HttpStatus> deny(@PathVariable Long id, @Valid @RequestBody DeletionAnswerDTO deletionAnswerDTO) {
        accountDeletionService.deny(id, deletionAnswerDTO.getReason());
        return ResponseEntity.noContent().build();
    }
}
