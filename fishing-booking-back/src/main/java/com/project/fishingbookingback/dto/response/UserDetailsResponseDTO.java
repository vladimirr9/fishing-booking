package com.project.fishingbookingback.dto.response;

public class UserDetailsResponseDTO {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String streetAndNumber;

    private String city;

    private String country;

    private Boolean firstLogin;


    public UserDetailsResponseDTO(Long id, String email, String firstName, String lastName, String phoneNumber, String streetAndNumber, String city, String country, Boolean firstLogin) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.country = country;
        this.firstLogin = firstLogin;
    }

    public UserDetailsResponseDTO() {
    }

    public Long getId() {
        return id;
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

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
