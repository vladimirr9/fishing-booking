package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class AdventureReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adventure_id", nullable = false)
    @JsonBackReference
    private FishingAdventure adventure;

    public AdventureReservation() {
    }

    @Override
    public String getOwnerEmail() {
        return adventure.getFishingInstructor().getEmail();
    }

    @Override
    public String getEntityName() {
        return this.adventure.getName();
    }

    @Override
    public Long getEntityId() {
        return adventure.getId();
    }

    public AdventureReservation(Long id, LocalDateTime startDate, LocalDateTime endDate, double price, boolean approved, FishingAdventure adventure, Client client, double serviceFee) {
        super(id, startDate, endDate, price, approved, client, serviceFee);
        this.adventure = adventure;
    }

    public FishingAdventure getAdventure() {
        return adventure;
    }

    public void setAdventure(FishingAdventure adventure) {
        this.adventure = adventure;
    }
}
