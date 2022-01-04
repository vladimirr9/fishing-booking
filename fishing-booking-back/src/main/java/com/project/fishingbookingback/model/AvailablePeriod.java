package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class AvailablePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;


    @ManyToOne()
    @JoinTable(
            name = "instructor_available_periods",
            joinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "instructor_id", referencedColumnName = "id")}

    )
    @JsonBackReference
    private FishingInstructor fishingInstructor;

    @ManyToOne()
    @JoinTable(
            name = "home_available_periods",
            joinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "home_id", referencedColumnName = "id")}

    )
    @JsonBackReference
    private HolidayHome holidayHome;

    @ManyToOne()
    @JoinTable(
            name = "boat_available_periods",
            joinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "boat_id", referencedColumnName = "id")}

    )
    @JsonBackReference
    private Boat boat;

    public AvailablePeriod(LocalDateTime fromTime, LocalDateTime toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;

    }

    public AvailablePeriod() {
    }

    public HolidayHome getHolidayHome() {return holidayHome;}
    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

}
