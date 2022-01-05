package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HolidayHomeReservation extends Reservation{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holidayhome_id")
    private HolidayHome holidayHome;
}
