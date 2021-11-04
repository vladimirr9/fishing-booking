package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.repository.AdventureRepository;
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
        return null;
    }
}
