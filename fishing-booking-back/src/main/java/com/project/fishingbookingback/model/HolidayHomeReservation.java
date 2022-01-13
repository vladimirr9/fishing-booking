package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class HolidayHomeReservation extends Reservation{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holidayhome_id")
    private HolidayHome holidayHome;

    public HolidayHomeReservation(Long id, LocalDateTime startDate, LocalDateTime endDate, double price, boolean approved, HolidayHome holidayHome) {
        super(id, startDate, endDate, price, approved);
        this.holidayHome = holidayHome;
    }

    public HolidayHomeReservation() {
    }

    public HolidayHome getHolidayHome() {
        return holidayHome;
    }

    public void setHolidayHome(HolidayHome holidayHome) {
        this.holidayHome = holidayHome;
    }
}
