package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.AdditionalService;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.FishingInstructor;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.repository.AdventureRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdventureService {
    private final UserService userService;
    private final AdventureRepository adventureRepository;
    private final LoggedUserService loggedUserService;

    public AdventureService(UserService userService, AdventureRepository adventureRepository, LoggedUserService loggedUserService) {
        this.userService = userService;
        this.adventureRepository = adventureRepository;
        this.loggedUserService = loggedUserService;
    }

    public FishingAdventure create(FishingAdventure fishingAdventure) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FishingInstructor fishingInstructor = (FishingInstructor) userService.findByEmail(auth.getName());
        fishingAdventure.setFishingInstructor(fishingInstructor);
        return adventureRepository.save(fishingAdventure);
    }

    public FishingAdventure findByID(Long id) {
        return adventureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(FishingAdventure.class.getSimpleName()));
    }

    public FishingAdventure addService(Long id, AdditionalService additionalService) {
        FishingAdventure fishingAdventure = findByID(id);
        fishingAdventure.addService(additionalService);
        return adventureRepository.save(fishingAdventure);
    }

    public FishingAdventure addPicture(Long id, Picture picture) {
        FishingAdventure fishingAdventure = findByID(id);
        fishingAdventure.addPicture(picture);
        return adventureRepository.save(fishingAdventure);
    }
}
