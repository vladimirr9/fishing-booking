package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityAlreadyExistsException;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.Role;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.repository.ProviderRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderRegistrationService {
    private final ProviderRegistrationRepository repository;
    private final UserService userService;
    private final EmailService emailService;

    public ProviderRegistrationService(ProviderRegistrationRepository repository, UserService userService, EmailService emailService) {
        this.repository = repository;
        this.userService = userService;
        this.emailService = emailService;
    }

    public ProviderRegistration findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProviderRegistration.class.getSimpleName()));
    }

    public List<ProviderRegistration> findAll(Role excluded) {
        return repository.findAllExcept(excluded);
    }

    public ProviderRegistration createRequest(ProviderRegistration providerRegistration) {
        String email = providerRegistration.getEmail();
        if (userService.userExists(email) || registrationExists(email)) {
            throw new EntityAlreadyExistsException(providerRegistration.getEmail());
        }
        return repository.save(providerRegistration);
    }

    public boolean registrationExists(String email) {
        return repository.findByEmail(email) != null;
    }

    public void approve(Long id) {
        ProviderRegistration providerRegistration = findById(id);
        User newUser = userService.createUser(providerRegistration);
        emailService.sendSimpleMessage(newUser.getEmail(), "Fishing Account Approved", "Your fishing booking account has been approved");
        repository.deleteById(id);
    }

    public void deny(Long id, String reason) {
        ProviderRegistration providerRegistration = findById(id);
        emailService.sendSimpleMessage(providerRegistration.getEmail(), "Fishing Account Denied", "The reason why your account has been denied:\n" + reason);
        repository.deleteById(id);
    }
}
