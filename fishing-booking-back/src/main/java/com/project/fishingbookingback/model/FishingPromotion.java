package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class FishingPromotion extends Promotion {

    @ManyToOne()
    @JoinColumn(name = "fishing_id", nullable = false)
    @JsonManagedReference
    private FishingAdventure fishingAdventure;

    private int peopleNumber;

    public FishingPromotion() {
    }

    public FishingPromotion(LocalDateTime fromTime, LocalDateTime toTime, Double price, LocalDateTime validUntil, FishingAdventure fishingAdventure, int peopleNumber) {
        super(fromTime, toTime, price, validUntil);
        this.fishingAdventure = fishingAdventure;
        this.peopleNumber = peopleNumber;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public FishingAdventure getFishingAdventure() {
        return fishingAdventure;
    }

    public double getStandardPrice() {
        return ChronoUnit.HOURS.between(getFromTime(), getToTime()) * fishingAdventure.getHourlyPrice() * peopleNumber;
    }
}
