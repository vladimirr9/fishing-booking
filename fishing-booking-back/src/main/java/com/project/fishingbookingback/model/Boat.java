package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Boat {
    public Boat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<BoatReservation> reservations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonManagedReference
    private BoatOwner boatOwner;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "boat_exterior_pictures",
            joinColumns = {@JoinColumn(name = "boat_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> exterior;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "boat_interior_pictures",
            joinColumns = {@JoinColumn(name = "boat_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> interior;
    private int capacity;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "boat_available_periods",
            joinColumns = {@JoinColumn(name = "boat_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")}
    )
    @JsonManagedReference
    private List<AvailablePeriod> availablePeriods;
    private String rulesOfConduct;
    private String additionalInfo;
    private String fishingEquipment;
    private Float cancellationFeePercentage;
    private Float pricePerDay;
    private double averageMark;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "boat_additional_service",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<AdditionalService> additionalService;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "subscribedBoats")
    @JsonManagedReference
    private List<Client> subscribedClients;

    public List<Client> getSubscribedClients() {
        return subscribedClients;
    }

    public void setSubscribedClients(List<Client> subscribedClients) {
        this.subscribedClients = subscribedClients;
    }

    public List<AdditionalService> getAdditionalService() {
        return additionalService;
    }

    public void addService(AdditionalService additionalService) {
        this.additionalService.add(additionalService);
    }

    public Boat(String name, String type, Float length, int engineNumber, Float enginePower, Float maxSpeed, Boolean gps, Boolean radar, Boolean vhf, Boolean fishfinder, Boolean cabin, Address address, String description, int capacity, String rulesOfConduct, String additionalInfo, String fishingEquipment, Float cancellationFeePercentage, Float pricePerDay) {
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
        this.address = address;
        this.description = description;
        this.capacity = capacity;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalInfo = additionalInfo;
        this.fishingEquipment = fishingEquipment;
        this.cancellationFeePercentage = cancellationFeePercentage;
        this.pricePerDay = pricePerDay;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Set<BoatReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<BoatReservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public int getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(int engineNumber) {
        this.engineNumber = engineNumber;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Boolean getGps() {
        return gps;
    }

    public void setGps(Boolean gps) {
        this.gps = gps;
    }

    public Boolean getRadar() {
        return radar;
    }

    public void setRadar(Boolean radar) {
        this.radar = radar;
    }

    public Boolean getVhf() {
        return vhf;
    }

    public void setVhf(Boolean vhf) {
        this.vhf = vhf;
    }

    public Boolean getFishfinder() {
        return fishfinder;
    }

    public void setFishfinder(Boolean fishfinder) {
        this.fishfinder = fishfinder;
    }

    public Boolean getCabin() {
        return cabin;
    }

    public void setCabin(Boolean cabin) {
        this.cabin = cabin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public List<Picture> getExterior() {
        return exterior;
    }

    public void setExterior(List<Picture> exterior) {
        this.exterior = exterior;
    }

    public List<Picture> getInterior() {
        return interior;
    }

    public void setInterior(List<Picture> interior) {
        this.interior = interior;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<AvailablePeriod> getAvailablePeriods() {
        return availablePeriods;
    }

    public void setAvailablePeriods(List<AvailablePeriod> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(String rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public Float getCancellationFeePercentage() {
        return cancellationFeePercentage;
    }

    public void setCancellationFeePercentage(Float cancellationFeePercentage) {
        this.cancellationFeePercentage = cancellationFeePercentage;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public void addPicture(Boolean is_interior, Picture picture) {
        if (is_interior) {
            interior.add(picture);
        } else {
            exterior.add(picture);
        }
    }

    public boolean isClientSubscribed(String clientUsername){
        for(Client client : getSubscribedClients())
            if(client.getEmail().equals(clientUsername))
                return true;
        return false;
    }
}
