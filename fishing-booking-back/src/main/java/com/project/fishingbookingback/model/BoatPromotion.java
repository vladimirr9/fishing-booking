package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class BoatPromotion extends Promotion {

    @ManyToOne()
    @JoinColumn(name = "boat_id", nullable = false)
    private Boat boat;

    public BoatPromotion() {
    }

    public BoatPromotion(LocalDateTime fromTime, LocalDateTime toTime, Double price, LocalDateTime validUntil, Boat boat) {
        super(fromTime, toTime, price, validUntil);
        this.boat = boat;
    }

    public Boat getBoat() {
        return boat;
    }

    public double getStandardPrice(){
       return ChronoUnit.DAYS.between(getFromTime(), getToTime()) * boat.getPricePerDay();
    }
}