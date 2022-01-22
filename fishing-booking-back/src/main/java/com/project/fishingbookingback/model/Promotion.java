package com.project.fishingbookingback.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Promotion {
    @Id
    @SequenceGenerator(name = "PromotionSeqGen", sequenceName = "PromotionSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PromotionSeqGen")
    private Long id;
    @NotNull
    private LocalDateTime fromTime;
    @NotNull
    private LocalDateTime toTime;
    @NotNull
    private Double price;
    @NotNull
    private LocalDateTime validUntil;

    public Promotion() {
    }

    public Promotion(LocalDateTime fromTime, LocalDateTime toTime, Double price, LocalDateTime validUntil) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.price = price;
        this.validUntil = validUntil;
    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public boolean overlapsForNewPromotion(LocalDateTime from, LocalDateTime to) {
        var startOverlaps =  !from.isBefore(this.fromTime) && from.isBefore(this.toTime);
        var endOvelaps = !to.isAfter(this.toTime) && to.isAfter(this.fromTime);
        return startOverlaps || endOvelaps;
    }
    public boolean overlaps(AvailablePeriod availablePeriod) {
        return overlapsForNewPromotion(availablePeriod.getFromTime(), availablePeriod.getToTime()) || (availablePeriod.getFromTime().isBefore(this.fromTime) && availablePeriod.getToTime().isAfter(this.toTime));
    }
    public boolean overlaps(Reservation reservation) {
        return overlapsForNewPromotion(reservation.getStartDate(), reservation.getEndDate()) || (reservation.getStartDate().isBefore(this.fromTime) && reservation.getEndDate().isAfter(this.toTime));
    }
    public boolean overlaps(Promotion promotion) {
        return overlapsForNewPromotion(promotion.getFromTime(), promotion.getToTime()) || (promotion.getFromTime().isBefore(this.fromTime) && promotion.getToTime().isAfter(this.toTime));
    }
}
