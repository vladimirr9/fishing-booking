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
}
