package com.project.fishingbookingback.dto.response;

import com.project.fishingbookingback.model.*;

import java.util.List;
import java.util.Set;

public class HomeResponseDTO {
    private Long id;
    private String name;
    private HomeOwner homeOwner;
    private Address address;
    private String description;
    private List<Picture> exterior;
    private List<Picture> interior;
    private int roomsPerHome;
    private int bedsPerRoom;
    private List<AvailablePeriod> availablePeriods;
    private String rulesOfConduct;
    private String additionalInfo;
    private float pricePerDay;
    private Set<HolidayHomeReservation> reservations;
    private List<AdditionalService> additionalService;
    private List<Client> subscribedClients;
    private double averageMark;
    private boolean occupied;

    public HomeResponseDTO(Long id, String name, HomeOwner homeOwner, Address address, String description, List<Picture> exterior, List<Picture> interior, int roomsPerHome, int bedsPerRoom, List<AvailablePeriod> availablePeriods, String rulesOfConduct, String additionalInfo, float pricePerDay, Set<HolidayHomeReservation> reservations, List<AdditionalService> additionalService, List<Client> subscribedClients, double averageMark, boolean occupied) {
        this.id = id;
        this.name = name;
        this.homeOwner = homeOwner;
        this.address = address;
        this.description = description;
        this.exterior = exterior;
        this.interior = interior;
        this.roomsPerHome = roomsPerHome;
        this.bedsPerRoom = bedsPerRoom;
        this.availablePeriods = availablePeriods;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalInfo = additionalInfo;
        this.pricePerDay = pricePerDay;
        this.reservations = reservations;
        this.additionalService = additionalService;
        this.subscribedClients = subscribedClients;
        this.averageMark = averageMark;
        this.occupied = occupied;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HomeOwner getHomeOwner() {
        return homeOwner;
    }

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public List<Picture> getExterior() {
        return exterior;
    }

    public List<Picture> getInterior() {
        return interior;
    }

    public int getRoomsPerHome() {
        return roomsPerHome;
    }

    public int getBedsPerRoom() {
        return bedsPerRoom;
    }

    public List<AvailablePeriod> getAvailablePeriods() {
        return availablePeriods;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public Set<HolidayHomeReservation> getReservations() {
        return reservations;
    }

    public List<AdditionalService> getAdditionalService() {
        return additionalService;
    }

    public List<Client> getSubscribedClients() {
        return subscribedClients;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
