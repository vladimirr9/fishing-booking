package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class HolidayHomeReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holidayhome_id")
    @JsonBackReference
    private HolidayHome holidayHome;

    public HolidayHomeReservation(Long id, LocalDateTime startDate, LocalDateTime endDate, double price, boolean approved, HolidayHome holidayHome, Client client, double serviceFee) {
        super(id, startDate, endDate, price, approved, client, serviceFee);
        this.holidayHome = holidayHome;
    }

    public HolidayHomeReservation() {
    }

    @Override
    public String getOwnerEmail() {
        return holidayHome.getHomeOwner().getEmail();
    }

    @Override
    public String getEntityName() {
        return holidayHome.getName();
    }

    @Override
    public Long getEntityId() {
        return holidayHome.getId();
    }

    public HolidayHome getHolidayHome() {
        return holidayHome;
    }

    public void setHolidayHome(HolidayHome holidayHome) {
        this.holidayHome = holidayHome;
    }
}
