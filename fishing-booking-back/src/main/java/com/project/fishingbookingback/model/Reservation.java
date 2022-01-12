package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reservation {
    @Id
    @SequenceGenerator(name="mySeqGen",sequenceName = "mySeq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "mySeqGen")
    private Long id;
    private LocalDateTime startDate;
    private int durationInHours;
    private double price;


    public Reservation() {
    }

    public Reservation(Long id, LocalDateTime startDate, int durationInHours, double price) {
        this.id = id;
        this.startDate = startDate;
        this.durationInHours = durationInHours;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

}
