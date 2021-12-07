package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateAdventureDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String biography;
    @Min(value = 0)
    private int maxPeople;
    @NotBlank
    private String rulesOfConduct;
    private String availableEquipment;
    @NotNull
    private Double cancellationFee;
    @NotBlank
    private String streetAndNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Double hourlyPrice;

    public UpdateAdventureDTO() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBiography() {
        return biography;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public String getAvailableEquipment() {
        return availableEquipment;
    }

    public double getCancellationFee() {
        return cancellationFee;
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

    public Double getHourlyPrice() {
        return hourlyPrice;
    }

    public Double getLongitude() {
        return longitude;
    }
}
