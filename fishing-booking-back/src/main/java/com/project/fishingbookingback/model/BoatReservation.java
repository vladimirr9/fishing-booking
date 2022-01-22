package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class BoatReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_id")
    @JsonBackReference
    private Boat boat;

    public BoatReservation(Long id, LocalDateTime startDate, LocalDateTime endDate, double price, boolean approved, Boat boat, Client client, double serviceFee) {
        super(id, startDate, endDate, price, approved, client, serviceFee);
        this.boat = boat;
    }

    public BoatReservation() {

    }

    @Override
    public String getOwnerEmail() {
        return boat.getBoatOwner().getEmail();
    }

    @Override
    public String getEntityName() {
        return this.boat.getName();
    }

    @Override
    public Long getEntityId() {
        return boat.getId();
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
