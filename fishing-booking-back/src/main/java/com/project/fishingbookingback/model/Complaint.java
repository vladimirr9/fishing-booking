package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "complaint_reservation_id", nullable = false)
    private Reservation reservation;
    private String content;

    public Complaint() {
    }

    public Complaint(Reservation reservation, String content) {
        this.reservation = reservation;
        this.content = content;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String getContent() {
        return content;
    }
}
