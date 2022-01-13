package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class BoatReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="boat_id")
    private Boat boat;

    public BoatReservation(Long id, LocalDateTime startDate, LocalDateTime endDate, double price, boolean approved, Boat boat) {
        super(id, startDate, endDate, price, approved);
        this.boat = boat;
    }

    public BoatReservation() {

    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
