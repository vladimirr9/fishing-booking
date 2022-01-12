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
    @JoinColumn(name = "adventure_id")
    @JsonBackReference
    private FishingAdventure adventure;

    public AdventureReservation() {
    }

    public AdventureReservation(Long id, LocalDateTime startDate, int durationInHours, double price, FishingAdventure adventure) {
        super(id, startDate, durationInHours, price);
        this.adventure = adventure;
    }

    public FishingAdventure getAdventure() {
        return adventure;
    }

    public void setAdventure(FishingAdventure adventure) {
        this.adventure = adventure;
    }
}
