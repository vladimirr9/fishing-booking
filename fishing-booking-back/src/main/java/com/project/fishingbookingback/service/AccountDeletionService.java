package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityAlreadyExistsException;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.AccountDeletion;
import com.project.fishingbookingback.repository.AccountDeletionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountDeletionService {
    private final AccountDeletionRepository repository;
    private final LoggedUserService loggedUserService;
    private final UserService userService;
    private final EmailService emailService;

    public AccountDeletionService(AccountDeletionRepository repository, LoggedUserService loggedUserService, UserService userService, EmailService emailService) {
        this.repository = repository;
        this.loggedUserService = loggedUserService;
        this.userService = userService;
        this.emailService = emailService;
    }

    public AccountDeletion create(AccountDeletion accountDeletion) {
        checkIfAllowed(accountDeletion.getEmail());
        userService.userExists(accountDeletion.getEmail());
        if (repository.existsByEmail(accountDeletion.getEmail())) {
            throw new EntityAlreadyExistsException(AccountDeletion.class.getSimpleName());
        }
        return repository.save(accountDeletion);
    }

    public AccountDeletion findOne(String email) {
        AccountDeletion accountDeletion = repository.findByEmail(email);
        if (accountDeletion == null) {
            throw new EntityNotFoundException(AccountDeletion.class.getSimpleName());
        }
        return accountDeletion;
    }


    public List<AccountDeletion> getAll() {
        return repository.findAll();
    }

    public AccountDeletion findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(AccountDeletion.class.getSimpleName()));
    }

    public void delete(String email) {
        checkIfAllowed(email);
        AccountDeletion accountDeletion = findOne(email);
        repository.deleteById(accountDeletion.getId());
    }

    private void checkIfAllowed(String email) {
        String userEmail = loggedUserService.getUsername();
        if (!userEmail.equals(email)) {
            throw new NotAllowedException();
        }
    }

    @Transactional
    public void approve(Long id, String reason) {
        AccountDeletion accountDeletion = repository.findOneById(id);
        emailService.sendSimpleMessage(accountDeletion.getEmail(), "Account deletion approved", reason);
        userService.deleteUser(accountDeletion.getEmail());
        repository.deleteById(id);
    }

    @Transactional
    public void deny(Long id, String reason) {
        AccountDeletion accountDeletion = repository.findOneById(id);
        emailService.sendSimpleMessage(accountDeletion.getEmail(), "Account deletion denied", reason);
        repository.deleteById(id);
    }
}
