package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
public class AvailablePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime fromTime;
    LocalDateTime toTime;
    @Email
    String email;

    public AvailablePeriod(LocalDateTime fromTime, LocalDateTime toTime, String email) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.email = email;
    }

    public AvailablePeriod() {
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

    public String getEmail() {
        return email;
    }
}
