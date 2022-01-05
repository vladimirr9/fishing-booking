package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AdventureReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="adventure_id")
    private FishingAdventure adventure;

    public AdventureReservation() {
    }

    public AdventureReservation(Long id, LocalDateTime startDate, int durationInHours, double mark, double price, FishingAdventure adventure) {
        super(id, startDate, durationInHours, mark, price);
        this.adventure = adventure;
    }

    public FishingAdventure getAdventure() {
        return adventure;
    }

    public void setAdventure(FishingAdventure adventure) {
        this.adventure = adventure;
    }
}
