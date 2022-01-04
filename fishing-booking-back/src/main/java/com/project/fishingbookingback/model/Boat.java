package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Boat {
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    private String description;
    @OneToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private BoatOwner boatOwner;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "exterior_pictures",
            joinColumns = {@JoinColumn(name = "boat_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> exterior;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "interior_pictures",
            joinColumns = {@JoinColumn(name = "boat_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private List<Picture> interior;
    private int capacity;
    @OneToMany(cascade = CascadeType.ALL)
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
}
