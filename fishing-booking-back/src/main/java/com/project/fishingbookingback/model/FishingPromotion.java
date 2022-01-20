package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class FishingPromotion extends Promotion {

    @ManyToOne()
    @JoinColumn(name = "fishing_id", nullable = false)
    private FishingAdventure fishingAdventure;

    public FishingPromotion() {
    }

    public FishingPromotion(LocalDateTime fromTime, LocalDateTime toTime, Double price, LocalDateTime validUntil, FishingAdventure fishingAdventure) {
        super(fromTime, toTime, price, validUntil);
        this.fishingAdventure = fishingAdventure;
    }

    public FishingAdventure getFishingAdventure() {
        return fishingAdventure;
    }

    public double getStandardPrice(){
        return ChronoUnit.HOURS.between(getFromTime(), getToTime()) * fishingAdventure.getHourlyPrice();
    }
}
