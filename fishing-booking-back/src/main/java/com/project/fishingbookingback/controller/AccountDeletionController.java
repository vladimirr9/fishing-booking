package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.model.AccountDeletion;
import com.project.fishingbookingback.service.AccountDeletionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/account-deletions")
public class AccountDeletionController {
    private final AccountDeletionService accountDeletionService;

    public AccountDeletionController(AccountDeletionService accountDeletionService) {
        this.accountDeletionService = accountDeletionService;
    }

    @PostMapping()
    public ResponseEntity<AccountDeletion> create(@Valid @RequestBody AccountDeletion accountDeletion) {
        AccountDeletion newAccountDeletion = accountDeletionService.create(accountDeletion);
        return ResponseEntity.ok(newAccountDeletion);
    }

    @GetMapping()
    public ResponseEntity<List<AccountDeletion>> getAll() {
        List<AccountDeletion> accountDeletions = accountDeletionService.getAll();
        return ResponseEntity.ok(accountDeletions);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<AccountDeletion> getOne(@PathVariable String email) {
        AccountDeletion accountDeletion = accountDeletionService.findOne(email);
        return ResponseEntity.ok(accountDeletion);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String email) {
        accountDeletionService.delete(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/approve")
    public ResponseEntity<HttpStatus> approve(@PathVariable Long id) {
        //TODO: implement
        return ResponseEntity.noContent().build();
    }
}
