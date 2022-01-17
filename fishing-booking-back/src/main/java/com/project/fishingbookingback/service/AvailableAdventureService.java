package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailableAdventureService {

    private final AdventureService adventureService;
    private final UserService userService;
    private final AvailablePeriodService availablePeriodService;

    public AvailableAdventureService(AdventureService adventureService, UserService userService, AvailablePeriodService availablePeriodService) {
        this.adventureService = adventureService;
        this.userService = userService;
        this.availablePeriodService = availablePeriodService;
    }

    public List<FishingAdventure> getAvailableAdventures(LocalDateTime from, LocalDateTime to){
        List<FishingAdventure> fishingAdventures = new ArrayList<>();
        for(User user : userService.findAll())
            if(user.getRole()== Role.ROLE_FISHING_INSTRUCTOR)
                fishingAdventures.addAll(getAvailableAdventuresForFishingInstructor(user.getEmail(),from,to));

        return fishingAdventures;
    }

    public boolean IsInstructorAvailable(String email,LocalDateTime from,LocalDateTime to){
        List<AvailablePeriod> availablePeriods = availablePeriodService.getPeriods(email);
        for(AvailablePeriod availablePeriod : availablePeriods)
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return true;
        return false;
    }

    private List<FishingAdventure> getAvailableAdventuresForFishingInstructor(String email,LocalDateTime from, LocalDateTime to) {
        List<AvailablePeriod> availablePeriods = availablePeriodService.getPeriods(email);
        for (AvailablePeriod availablePeriod : availablePeriods)
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return adventureService.getAdventures(email, null);

        return new ArrayList<>();
    }

}
