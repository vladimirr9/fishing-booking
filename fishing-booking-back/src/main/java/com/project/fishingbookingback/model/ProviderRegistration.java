package com.project.fishingbookingback.model;

import com.project.fishingbookingback.validator.RoleSubset;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class ProviderRegistration {
    public ProviderRegistration() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @RoleSubset(anyOf = {Role.ROLE_BOAT_OWNER, Role.ROLE_HOME_OWNER, Role.ROLE_FISHING_INSTRUCTOR})
    private Role role;
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Street cannot be blank")
    private String streetAndNumber;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotBlank(message = "Country cannot be blank")
    private String country;
    @NotBlank(message = "Explanation cannot be blank")
    private String explanation;

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getExplanation() {
        return explanation;
    }
}
