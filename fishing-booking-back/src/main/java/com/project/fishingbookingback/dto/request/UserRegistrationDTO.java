package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegistrationDTO {
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Street cannot be blank")
    private String streetAndNumber;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotBlank(message = "Country cannot be blank")
    private String country;


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
}
