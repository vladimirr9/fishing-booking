package com.project.fishingbookingback.model;

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
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    public Address address;
    @OneToMany()
    @JoinTable(
            name = "adventure_available_time",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "time_id")}
    )
    public List<AvailableTime> availableTime;

    @OneToMany()
    @JoinTable(
            name = "adventure_additional_service",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    public List<AdditionalService> additionalService;

    @OneToOne()
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    public FishingInstructor fishingInstructor;

}
