package com.project.fishingbookingback.dto.request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.fishingbookingback.model.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class NewBoatDTO {
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
    private String rulesOfConduct;
    private String additionalInfo;
    @NotBlank
    private String type;
    @NotNull
    private Float length;
    @NotNull
    private int engineNumber;
    @NotNull
    private Float enginePower;
    @NotNull
    private Float maxSpeed;
    @NotNull
    private Boolean gps;
    @NotNull
    private Boolean radar;
    @NotNull
    private Boolean vhf;
    @NotNull
    private Boolean fishfinder;
    @NotNull
    private Boolean cabin;
    @NotNull
    private int capacity;
    private String fishingEquipment;
    @NotNull
    private Float cancellationFeePercentage;
    @NotNull
    private Float pricePerDay;

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

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getType() {
        return type;
    }

    public Float getLength() {
        return length;
    }

    public int getEngineNumber() {
        return engineNumber;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public Boolean getGps() {
        return gps;
    }

    public Boolean getRadar() {
        return radar;
    }

    public Boolean getVhf() {
        return vhf;
    }

    public Boolean getFishfinder() {
        return fishfinder;
    }

    public Boolean getCabin() {
        return cabin;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public Float getCancellationFeePercentage() {
        return cancellationFeePercentage;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }
}
