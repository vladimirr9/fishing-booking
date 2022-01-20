package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
