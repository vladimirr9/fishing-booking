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
    private final UserService userService;

    public AvailablePeriodService(AvailablePeriodRepository repository, LoggedUserService loggedUserService, UserService userService) {
        this.repository = repository;
        this.loggedUserService = loggedUserService;
        this.userService = userService;
    }

    public AvailablePeriod create(AvailablePeriod availablePeriod, String email) {
        checkIfAllowed(email);
        AvailablePeriod savedAvailablePeriod = repository.save(availablePeriod);
        userService.addAvailablePeriod(email, savedAvailablePeriod);
        return savedAvailablePeriod;
    }

    public List<AvailablePeriod> getPeriods(String email) {
        return repository.findAll(email);
    }

    public AvailablePeriod findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(AvailablePeriod.class.getSimpleName()));
    }

    public void delete(Long id) {
        AvailablePeriod availablePeriod = findByID(id);
        userService.removeAvailablePeriod(availablePeriod);
    }


    private void checkIfAllowed(String email) {
        String userEmail = loggedUserService.getUsername();
        if (!userEmail.equals(email)) {
            throw new NotAllowedException();
        }
    }


}
