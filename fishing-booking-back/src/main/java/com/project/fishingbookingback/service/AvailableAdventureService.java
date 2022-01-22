package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailableAdventureService {

    private final AdventureService adventureService;
    private final UserService userService;

    public AvailableAdventureService(AdventureService adventureService, UserService userService) {
        this.adventureService = adventureService;
        this.userService = userService;
    }

    public void reservePeriod(String instructorEmail,LocalDateTime from,LocalDateTime to){
        AvailablePeriod availablePeriod = IsInstructorAvailable(instructorEmail,from,to);
        FishingInstructor instructor = (FishingInstructor) userService.findByEmail(instructorEmail);
        instructor.getAvailablePeriods().remove(availablePeriod);

        var sliceBefore = availablePeriod.sliceBefore(from);
        if(sliceBefore!=null)
            instructor.getAvailablePeriods().add(sliceBefore);

        var sliceAfter = availablePeriod.sliceAfter(to);
        if(sliceAfter!= null)
            instructor.getAvailablePeriods().add(sliceAfter);

        userService.updateUser(instructor);
    }

    public List<FishingAdventure> getAvailableAdventures(LocalDateTime from, LocalDateTime to){
        if(from.isAfter(to)) throw new NotAllowedException();
        List<FishingAdventure> fishingAdventures = new ArrayList<>();
        for(User user : userService.findAll())
            if(user.getRole()== Role.ROLE_FISHING_INSTRUCTOR)
                fishingAdventures.addAll(getAvailableAdventuresForFishingInstructor(user.getEmail(),from,to));

        return fishingAdventures;
    }


    private List<FishingAdventure> getAvailableAdventuresForFishingInstructor(String email,LocalDateTime from, LocalDateTime to) {
        List<AvailablePeriod> availablePeriods = ((FishingInstructor) userService.findByEmail(email)).getAvailablePeriods();
        for (AvailablePeriod availablePeriod : availablePeriods)
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return adventureService.getAdventures(email, null);

        return new ArrayList<>();
    }

    private AvailablePeriod IsInstructorAvailable(String email,LocalDateTime from,LocalDateTime to){
        List<AvailablePeriod> availablePeriods = ((FishingInstructor) userService.findByEmail(email)).getAvailablePeriods();
        for(AvailablePeriod availablePeriod : availablePeriods)
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return availablePeriod;
        throw new EntityNotFoundException(availablePeriods.getClass().toString());
    }

}
