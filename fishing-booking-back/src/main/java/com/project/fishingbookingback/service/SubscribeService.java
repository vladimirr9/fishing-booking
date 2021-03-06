package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.NotAllowedException;
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

    public void unsubscribeBoat(String userEmail, Long boatId) {
        Client client = (Client) userService.findByEmail(userEmail);
        Boat boat = boatService.findByID(boatId);
        boat.getSubscribedClients().removeIf(c -> (c.getId()==client.getId()));
        client.getSubscribedBoats().removeIf(b ->(b.getId()==boatId));
        boatService.save(boat);
    }

    public void unsubscribeHome(String userEmail, Long homeId) {
        Client client = (Client) userService.findByEmail(userEmail);
        HolidayHome home = holidayHomeService.findByID(homeId);
        client.getSubscribedHomes().removeIf(h->(h.getId()==homeId));
        home.getSubscribedClients().removeIf(c -> (c.getId()==client.getId()));
        holidayHomeService.save(home);
    }

    public void unsubscribeAdventure(String userEmail, Long adventureId) {
        Client client = (Client) userService.findByEmail(userEmail);
        FishingAdventure fishingAdventure = adventureService.findByID(adventureId);
        client.getSubscribedAdventures().removeIf(a->(a.getId()==adventureId));
        fishingAdventure.getSubscribedClients().removeIf(c -> (c.getId()==client.getId()));
        adventureService.saveAdventure(fishingAdventure);
    }

    public Boolean isBoatSubscribed(String userEmail, Long boatId) {
        Client client = (Client) userService.findByEmail(userEmail);
        Boat boat = boatService.findByID(boatId);
        return boat.getSubscribedClients().stream().filter(c-> c.getId()==client.getId()).findFirst().isPresent();
    }

    public Boolean isHomeSubscribed(String userEmail, Long homeId) {
        Client client = (Client) userService.findByEmail(userEmail);
        HolidayHome home = holidayHomeService.findByID(homeId);
        return home.getSubscribedClients().stream().filter(c-> c.getId()==client.getId()).findFirst().isPresent();
    }

    public Boolean isAdventureSubscribed(String userEmail, Long adventureId) {
        Client client = (Client) userService.findByEmail(userEmail);
        FishingAdventure fishingAdventure = adventureService.findByID(adventureId);
        return fishingAdventure.getSubscribedClients().stream().filter(c-> c.getId()==client.getId()).findFirst().isPresent();
    }
}
