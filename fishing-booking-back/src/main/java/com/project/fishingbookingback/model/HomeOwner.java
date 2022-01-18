package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("HOME_OWNER")
public class HomeOwner extends User {
    public HomeOwner() {
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "homeOwner")
    @JsonBackReference
    private List<HolidayHome> holidayHomes;

    public HomeOwner(ProviderRegistration providerRegistration) {
        super(providerRegistration);
    }

    public List<HolidayHome> getHolidayHomes() {
        return holidayHomes;
    }
}
