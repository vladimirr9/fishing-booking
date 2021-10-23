package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {
    public Address() {
    }

    public Address(ProviderRegistration providerRegistration) {
        this.streetAndNumber = Objects.requireNonNull(providerRegistration.getStreetAndNumber());
        this.city = Objects.requireNonNull(providerRegistration.getCity());
        this.country = Objects.requireNonNull(providerRegistration.getCountry());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetAndNumber;
    private String city;
    private String country;
    private Long latitude;
    private Long longitude;

    public Long getId() {
        return id;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Long getLatitude() {
        return latitude;
    }

    public Long getLongitude() {
        return longitude;
    }
}
