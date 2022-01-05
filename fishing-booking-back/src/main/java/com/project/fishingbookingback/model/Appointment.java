package com.project.fishingbookingback.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime beginning;
    private int durationInHours;
    private int capacity;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "appointment_additional_service",
            joinColumns = {@JoinColumn(name = "adventure_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<AdditionalService> additionalServices;
    private int price;
}
