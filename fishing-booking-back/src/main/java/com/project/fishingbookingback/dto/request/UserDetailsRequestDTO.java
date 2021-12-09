package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.NotBlank;

public class UserDetailsRequestDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String streetAndNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String country;


    public UserDetailsRequestDTO(String email, String firstName, String lastName, String phoneNumber, String streetAndNumber, String city, String country) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.country = country;
    }

    public UserDetailsRequestDTO() {
    }
    

    public String getEmail() {
        return email;
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

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
