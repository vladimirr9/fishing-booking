package com.project.fishingbookingback.dto.response;

import com.project.fishingbookingback.model.*;

import java.util.List;
import java.util.Set;

public class BoatResponseDTO {
    private Long id;
    private String name;
    private String type;
    private Float length;
    private int engineNumber;
    private Float enginePower;
    private Float maxSpeed;
    private Boolean gps;
    private Boolean radar;
    private Boolean vhf;
    private Boolean fishfinder;
    private Boolean cabin;
    private Set<BoatReservation> reservations;
    private Address address;
    private String description;
    private BoatOwner boatOwner;
    private List<Picture> exterior;
    private List<Picture> interior;
    private int capacity;
    private List<AvailablePeriod> availablePeriods;
    private String rulesOfConduct;
    private String additionalInfo;
    private String fishingEquipment;
    private Float cancellationFeePercentage;
    private Float pricePerDay;
    private double averageMark;
    private List<AdditionalService> additionalService;
    private List<Client> subscribedClients;
    private boolean occupied;

    public BoatResponseDTO(Long id, String name, String type, Float length, int engineNumber, Float enginePower, Float maxSpeed, Boolean gps, Boolean radar, Boolean vhf, Boolean fishfinder, Boolean cabin, Set<BoatReservation> reservations, Address address, String description, BoatOwner boatOwner, List<Picture> exterior, List<Picture> interior, int capacity, List<AvailablePeriod> availablePeriods, String rulesOfConduct, String additionalInfo, String fishingEquipment, Float cancellationFeePercentage, Float pricePerDay, double averageMark, List<AdditionalService> additionalService, List<Client> subscribedClients, boolean occupied) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.length = length;
        this.engineNumber = engineNumber;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.gps = gps;
        this.radar = radar;
        this.vhf = vhf;
        this.fishfinder = fishfinder;
        this.cabin = cabin;
        this.reservations = reservations;
        this.address = address;
        this.description = description;
        this.boatOwner = boatOwner;
        this.exterior = exterior;
        this.interior = interior;
        this.capacity = capacity;
        this.availablePeriods = availablePeriods;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalInfo = additionalInfo;
        this.fishingEquipment = fishingEquipment;
        this.cancellationFeePercentage = cancellationFeePercentage;
        this.pricePerDay = pricePerDay;
        this.averageMark = averageMark;
        this.additionalService = additionalService;
        this.subscribedClients = subscribedClients;
        this.occupied = occupied;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Float getLength() {
        return length;
    }

    public int getEngineNumber() {
        return engineNumber;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public Boolean getGps() {
        return gps;
    }

    public Boolean getRadar() {
        return radar;
    }

    public Boolean getVhf() {
        return vhf;
    }

    public Boolean getFishfinder() {
        return fishfinder;
    }

    public Boolean getCabin() {
        return cabin;
    }

    public Set<BoatReservation> getReservations() {
        return reservations;
    }

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public List<Picture> getExterior() {
        return exterior;
    }

    public List<Picture> getInterior() {
        return interior;
    }

    public int getCapacity() {
        return capacity;
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

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public Float getCancellationFeePercentage() {
        return cancellationFeePercentage;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public List<AdditionalService> getAdditionalService() {
        return additionalService;
    }

    public List<Client> getSubscribedClients() {
        return subscribedClients;
    }

    public boolean getOccupied() {
        return occupied;
    }
}
