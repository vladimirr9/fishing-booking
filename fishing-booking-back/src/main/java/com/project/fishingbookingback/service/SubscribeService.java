package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.Client;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.HolidayHome;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {
    private final UserService userService;
    private final BoatService boatService;
    private final HolidayHomeService holidayHomeService;
    private final AdventureService adventureService;

    public SubscribeService(UserService userService, BoatService boatService, HolidayHomeService holidayHomeService, AdventureService adventureService) {
        this.userService = userService;
        this.boatService = boatService;
        this.holidayHomeService = holidayHomeService;
        this.adventureService = adventureService;
    }

    public void subscribeHome(String userEmail,Long homeId){
        Client client = (Client) userService.findByEmail(userEmail);
        HolidayHome home = holidayHomeService.findByID(homeId);
        client.getSubscribedHomes().add(home);
        home.getSubscribedClients().add(client);
        holidayHomeService.save(home);
    }

    public void subscribeBoat(String userEmail,Long boatId){
        Client client = (Client) userService.findByEmail(userEmail);
        Boat boat = boatService.findByID(boatId);
        client.getSubscribedBoats().add(boat);
        boat.getSubscribedClients().add(client);
        boatService.save(boat);
    }

    public void subscribeAdventure(String userEmail,Long adventureId){
        Client client = (Client) userService.findByEmail(userEmail);
        FishingAdventure fishingAdventure = adventureService.findByID(adventureId);
        client.getSubscribedAdventures().add(fishingAdventure);
        fishingAdventure.getSubscribedClients().add(client);
        adventureService.saveAdventure(fishingAdventure);
    }
}
