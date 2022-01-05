package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class BoatReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="boat_id")
    private Boat boat;

    public BoatReservation(Long id, LocalDateTime startDate, int durationInHours, double mark, double price, Boat boat) {
        super(id, startDate, durationInHours, mark, price);
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
