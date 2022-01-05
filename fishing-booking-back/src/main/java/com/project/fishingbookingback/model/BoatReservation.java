package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BoatReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="boat_id")
    private Boat boat;

    public BoatReservation(LocalDate startDate, int durationInHours, double mark, Boat boat) {
        super(startDate, durationInHours, mark);
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
