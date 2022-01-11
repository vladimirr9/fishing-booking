package com.project.fishingbookingback.dto.request;

import com.project.fishingbookingback.model.Picture;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NewHolidayHomeDTO {
    @NotBlank
    private String name;
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
    @NotBlank
    private String description;
    private List<Picture> exterior;
    private List<Picture> interior;
    @NotNull
    private int roomsPerHome;
    @NotNull
    private int bedsPerRoom;
    private String rulesOfConduct;
    private String additionalInfo;

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public List<Picture> getExterior() {
        return exterior;
    }

    public List<Picture> getInterior() {
        return interior;
    }

    public int getRoomsPerHome() {
        return roomsPerHome;
    }

    public int getBedsPerRoom() {
        return bedsPerRoom;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
