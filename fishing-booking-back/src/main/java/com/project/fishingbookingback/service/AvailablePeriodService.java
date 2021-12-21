package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.AvailablePeriod;
import com.project.fishingbookingback.repository.AvailablePeriodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailablePeriodService {
    private final AvailablePeriodRepository repository;
    private final LoggedUserService loggedUserService;

    public AvailablePeriodService(AvailablePeriodRepository repository, LoggedUserService loggedUserService) {
        this.repository = repository;
        this.loggedUserService = loggedUserService;
    }

    public AvailablePeriod create(AvailablePeriod availablePeriod) {
        checkIfAllowed(availablePeriod.getEmail());
        return repository.save(availablePeriod);
    }

    public List<AvailablePeriod> getPeriods(String providerEmail) {
        return repository.findAll(providerEmail);
    }

    public AvailablePeriod findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(AvailablePeriod.class.getSimpleName()));
    }

    public void delete(Long id) {
        AvailablePeriod availablePeriod = findByID(id);
        checkIfAllowed(availablePeriod.getEmail());
        repository.deleteById(id);
    }


    private void checkIfAllowed(String email) {
        String userEmail = loggedUserService.getUsername();
        if (!userEmail.equals(email)) {
            throw new NotAllowedException();
        }
    }


}
