package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Entity
public class HolidayHome {
    public HolidayHome() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonManagedReference
    private HomeOwner homeOwner;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "holiday_home_exterior_pictures",
            joinColumns = {@JoinColumn(name = "holiday_home_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> exterior;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "holiday_home_interior_pictures",
            joinColumns = {@JoinColumn(name = "holiday_home_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> interior;
    private int roomsPerHome;
    private int bedsPerRoom;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(
            name = "home_available_periods",
            joinColumns = {@JoinColumn(name = "holiday_home_id")},
            inverseJoinColumns = {@JoinColumn(name = "available_periods_id")}
    )
    @JsonManagedReference
    private List<AvailablePeriod> availablePeriods;
    private String rulesOfConduct;
    private String additionalInfo;
    private float pricePerDay;
    @OneToMany(mappedBy = "holidayHome", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<HolidayHomeReservation> reservations;

    public Set<HolidayHomeReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<HolidayHomeReservation> reservations) {
        this.reservations = reservations;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setHomeOwner(HomeOwner homeOwner) {
        this.homeOwner = homeOwner;
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


    public List<AvailablePeriod> getAvailablePeriods() {
        return availablePeriods;
    }

    public void setAvailablePeriods(List<AvailablePeriod> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HomeOwner getHomeOwner() {
        return homeOwner;
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

    public int getRoomsPerHome() {
        return roomsPerHome;
    }

    public void setRoomsPerHome(int roomsPerHome) {
        this.roomsPerHome = roomsPerHome;
    }

    public int getBedsPerRoom() {
        return bedsPerRoom;
    }

    public void setBedsPerRoom(int bedsPerRoom) {
        this.bedsPerRoom = bedsPerRoom;
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

    public HolidayHome(String name, Address address, String description, List<Picture> exterior, List<Picture> interior, int roomsPerHome, int bedsPerRoom, List<AvailablePeriod> availablePeriods, String rulesOfConduct, String additionalInfo) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.exterior = exterior;
        this.interior = interior;
        this.roomsPerHome = roomsPerHome;
        this.bedsPerRoom = bedsPerRoom;
        this.availablePeriods = availablePeriods;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalInfo = additionalInfo;
    }

    public void addPicture(Boolean is_interior, Picture picture) {
        if (is_interior) {
            interior.add(picture);
        } else {
            exterior.add(picture);
        }
    }
}
