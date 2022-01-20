package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class HolidayHomePromotion extends Promotion {

    @ManyToOne()
    @JoinColumn(name = "holidayHome_id", nullable = false)
    private HolidayHome holidayHome;

    public HolidayHomePromotion() {
    }

    public HolidayHomePromotion(LocalDateTime fromTime, LocalDateTime toTime, Double price, LocalDateTime validUntil, HolidayHome holidayHome) {
        super(fromTime, toTime, price, validUntil);
        this.holidayHome = holidayHome;
    }

    public HolidayHome getHolidayHome() {
        return holidayHome;
    }

    public double getStandardPrice(){
        return ChronoUnit.DAYS.between(getFromTime(), getToTime()) * holidayHome.getPricePerDay();
    }
}