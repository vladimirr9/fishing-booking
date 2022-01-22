package com.project.fishingbookingback.dto.response;

import com.project.fishingbookingback.model.*;

import java.util.List;
import java.util.Set;

public class AdventureResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String biography;
    private List<Picture> pictures;
    private int maxPeople;
    private String rulesOfConduct;
    private String availableEquipment;
    private double cancellationFee;
    private Address address;
    private Set<AdventureReservation> reservations;
    private Set<FishingPromotion> fishingPromotions;
    private List<AdditionalService> additionalService;
    private FishingInstructor fishingInstructor;
    private List<Client> subscribedClients;
    private Double hourlyPrice;
    private double averageMark;
    private boolean occupied;

    public AdventureResponseDTO(Long id, String name, String description, String biography, List<Picture> pictures, int maxPeople, String rulesOfConduct, String availableEquipment, double cancellationFee, Address address, Set<AdventureReservation> reservations, Set<FishingPromotion> fishingPromotions, List<AdditionalService> additionalService, FishingInstructor fishingInstructor, List<Client> subscribedClients, Double hourlyPrice, double averageMark, boolean occupied) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.biography = biography;
        this.pictures = pictures;
        this.maxPeople = maxPeople;
        this.rulesOfConduct = rulesOfConduct;
        this.availableEquipment = availableEquipment;
        this.cancellationFee = cancellationFee;
        this.address = address;
        this.reservations = reservations;
        this.fishingPromotions = fishingPromotions;
        this.additionalService = additionalService;
        this.fishingInstructor = fishingInstructor;
        this.subscribedClients = subscribedClients;
        this.hourlyPrice = hourlyPrice;
        this.averageMark = averageMark;
        this.occupied = occupied;
    }

    public Long getId() {
        return id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBiography() {
        return biography;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public String getAvailableEquipment() {
        return availableEquipment;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public Address getAddress() {
        return address;
    }

    public Set<AdventureReservation> getReservations() {
        return reservations;
    }

    public Set<FishingPromotion> getFishingPromotions() {
        return fishingPromotions;
    }

    public List<AdditionalService> getAdditionalService() {
        return additionalService;
    }

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public List<Client> getSubscribedClients() {
        return subscribedClients;
    }

    public Double getHourlyPrice() {
        return hourlyPrice;
    }

    public double getAverageMark() {
        return averageMark;
    }
}
