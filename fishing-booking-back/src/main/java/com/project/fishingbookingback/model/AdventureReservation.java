package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AdventureReservation extends Reservation {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="adventure_id")
    private FishingAdventure adventure;

    public AdventureReservation() {
    }

    public AdventureReservation(LocalDate startDate, int durationInHours, double mark, FishingAdventure adventure) {
        super(startDate, durationInHours, mark);
        this.adventure = adventure;
    }

    public FishingAdventure getAdventure() {
        return adventure;
    }

    public void setAdventure(FishingAdventure adventure) {
        this.adventure = adventure;
    }
}
