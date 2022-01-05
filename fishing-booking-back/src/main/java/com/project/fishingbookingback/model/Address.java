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

    public Address(String streetAndNumber, String city, String country, Double latitude, Double longitude) {
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetAndNumber;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;

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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return city + ", " + streetAndNumber;
    }
}
