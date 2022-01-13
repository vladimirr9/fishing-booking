package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Reservation reservation;
    private String comment;
    private boolean sanction;
    private boolean appeared;
    private boolean approved;

    public Report(String comment, boolean sanction, boolean appeared) {
        this.comment = comment;
        this.sanction = sanction;
        this.appeared = appeared;
        this.approved = false;
        this.reservation = null;
    }


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public boolean isSanction() {
        return sanction;
    }

    public boolean isAppeared() {
        return appeared;
    }

    public Report() {
    }
}
