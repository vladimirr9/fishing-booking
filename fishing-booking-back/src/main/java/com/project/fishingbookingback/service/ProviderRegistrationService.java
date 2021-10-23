package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.repository.ProviderRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderRegistrationService {
    private final ProviderRegistrationRepository repository;

    public ProviderRegistrationService(ProviderRegistrationRepository repository) {
        this.repository = repository;
    }

    public ProviderRegistration findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProviderRegistration.class.getName()));
    }

    public List<ProviderRegistration> findAll() {
        return repository.findAll();
    }

    public ProviderRegistration createRequest(ProviderRegistration providerRegistration) {
        return repository.save(providerRegistration);
    }
}
