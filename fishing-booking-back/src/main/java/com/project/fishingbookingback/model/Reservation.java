package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Reservation {
    @Id
    @SequenceGenerator(name="mySeqGen",sequenceName = "mySeq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "mySeqGen")
    private Long id;
    private LocalDate startDate;
    private int durationInHours;
    private double mark;

    public Reservation() {
    }

    public Reservation(LocalDate startDate, int durationInHours, double mark) {
        this.startDate = startDate;
        this.durationInHours = durationInHours;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
