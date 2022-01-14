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

    public HolidayHomeReservation(Long id, LocalDateTime startDate, LocalDateTime endDate, double price, boolean approved, HolidayHome holidayHome, Client client) {
        super(id, startDate, endDate, price, approved, client);
        this.holidayHome = holidayHome;
    }

    public HolidayHomeReservation() {
    }

    @Override
    public String getOwnerEmail() {
        return holidayHome.getHomeOwner().getEmail();
    }

    public HolidayHome getHolidayHome() {
        return holidayHome;
    }

    public void setHolidayHome(HolidayHome holidayHome) {
        this.holidayHome = holidayHome;
    }
}
