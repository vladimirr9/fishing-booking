package com.project.fishingbookingback.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fishing_adventure")
public class FishingAdventure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String biography;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "adventure_pictures",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> pictures;

    public void addPicture(Picture picture) {
        this.pictures.add(picture);
    }

    private int maxPeople;
    private String rulesOfConduct;
    private String availableEquipment;
    private double cancellationFee;
    private String priceList;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @OneToMany(mappedBy = "adventure",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<AdventureReservation> reservations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "adventure_additional_service",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<AdditionalService> additionalService;

    public void addService(AdditionalService additionalService) {
        this.additionalService.add(additionalService);
    }

    @ManyToOne()
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FishingInstructor fishingInstructor;

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    private Double hourlyPrice;

    public FishingAdventure() {
    }

    public FishingAdventure(String name, String description, String biography, int maxPeople, String rulesOfConduct, String availableEquipment, double cancellationFee, Double hourlyPrice, Address address) {
        this.name = name;
        this.description = description;
        this.biography = biography;
        this.maxPeople = maxPeople;
        this.rulesOfConduct = rulesOfConduct;
        this.availableEquipment = availableEquipment;
        this.cancellationFee = cancellationFee;
        this.hourlyPrice = hourlyPrice;
        this.address = address;
        this.additionalService = new ArrayList<>();
        this.pictures = new ArrayList<>();

    }

    public Set<AdventureReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<AdventureReservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public Double getHourlyPrice() {
        return hourlyPrice;
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

    public String getPriceList() {
        return priceList;
    }

    public Address getAddress() {
        return address;
    }


    public List<AdditionalService> getAdditionalService() {
        return additionalService;
    }

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setRulesOfConduct(String rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public void setAvailableEquipment(String availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void setAdditionalService(List<AdditionalService> additionalService) {
        this.additionalService = additionalService;
    }

    public void setHourlyPrice(Double hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }
}
