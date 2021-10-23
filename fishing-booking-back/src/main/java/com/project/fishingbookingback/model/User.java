package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class User {
    public User() {
    }

    public User(ProviderRegistration providerRegistration) {
        this.email = Objects.requireNonNull(providerRegistration.getEmail());
        this.password = Objects.requireNonNull(providerRegistration.getPassword());
        this.firstName = Objects.requireNonNull(providerRegistration.getFirstName());
        this.lastName = Objects.requireNonNull(providerRegistration.getLastName());
        this.phoneNumber = Objects.requireNonNull(providerRegistration.getPhoneNumber());
        this.role = Objects.requireNonNull(providerRegistration.getRole());
        this.address = new Address(Objects.requireNonNull(providerRegistration));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    public Role role;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
