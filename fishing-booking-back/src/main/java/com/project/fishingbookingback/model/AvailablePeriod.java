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
    Long id;
    LocalDateTime fromTime;
    LocalDateTime toTime;


    @ManyToOne()
    @JoinTable(
            name = "instructor_available_periods",
            joinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "instructor_id", referencedColumnName = "id")}

    )
    @JsonBackReference
    private FishingInstructor fishingInstructor;


    public AvailablePeriod(LocalDateTime fromTime, LocalDateTime toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;

    }

    public AvailablePeriod() {
    }

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
