package com.project.fishingbookingback.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "fishing_adventure")
public class FishingAdventure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String biography;

    @OneToMany()
    @JoinTable(
            name = "adventure_pictures",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> pictures;
    private int maxPeople;
    private String rulesOfConduct;
    private String availableEquipment;
    private double cancellationFee;
    private String priceList;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @OneToMany()
    @JoinTable(
            name = "adventure_available_time",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "time_id")}
    )
    private List<AvailableTime> availableTime;

    @OneToMany()
    @JoinTable(
            name = "adventure_additional_service",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<AdditionalService> additionalService;

    @OneToOne()
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private FishingInstructor fishingInstructor;

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    public FishingAdventure() {
    }

    public FishingAdventure(String name, String description, String biography, int maxPeople, String rulesOfConduct, String availableEquipment, double cancellationFee, String priceList, Address address) {
        this.name = name;
        this.description = description;
        this.biography = biography;
        this.maxPeople = maxPeople;
        this.rulesOfConduct = rulesOfConduct;
        this.availableEquipment = availableEquipment;
        this.cancellationFee = cancellationFee;
        this.priceList = priceList;
        this.address = address;
    }

    public Long getId() {
        return id;
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

    public List<AvailableTime> getAvailableTime() {
        return availableTime;
    }

    public List<AdditionalService> getAdditionalService() {
        return additionalService;
    }

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }
}
