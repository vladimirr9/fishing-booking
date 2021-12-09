package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class HolidayHome {
    public HolidayHome(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private HomeOwner homeOwner;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "exterior_pictures",
            joinColumns = {@JoinColumn(name = "holiday_home_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> exterior;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "interior_pictures",
            joinColumns = {@JoinColumn(name = "holiday_home_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> interior;
    private int roomsPerHome;
    private int bedsPerRoom;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "holiday_home_appointments",
            joinColumns = {@JoinColumn(name = "holiday_home_id")},
            inverseJoinColumns = {@JoinColumn(name = "appointment_id")}
    )
    private List<Appointment> freeAppointments;
    private String rulesOfConduct;
    private String aditionalInfo;

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

    public List<Appointment> getFreeAppointments() {
        return freeAppointments;
    }

    public void setFreeAppointments(List<Appointment> freeAppointments) {
        this.freeAppointments = freeAppointments;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(String rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public String getAditionalInfo() {
        return aditionalInfo;
    }

    public void setAditionalInfo(String aditionalInfo) {
        this.aditionalInfo = aditionalInfo;
    }

    public HolidayHome(String name, Address address, String description, List<Picture> exterior, List<Picture> interior, int roomsPerHome, int bedsPerRoom, List<Appointment> freeAppointments, String rulesOfConduct, String aditionalInfo) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.exterior = exterior;
        this.interior = interior;
        this.roomsPerHome = roomsPerHome;
        this.bedsPerRoom = bedsPerRoom;
        this.freeAppointments = freeAppointments;
        this.rulesOfConduct = rulesOfConduct;
        this.aditionalInfo = aditionalInfo;
    }
}
