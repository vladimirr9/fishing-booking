package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("FISHING_INSTRUCTOR")
public class FishingInstructor extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "instructor_available_periods",
            joinColumns = {@JoinColumn(name = "instructor_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")}
    )
    @JsonManagedReference
    private List<AvailablePeriod> availablePeriods;

    public List<AvailablePeriod> getAvailablePeriods() {
        return availablePeriods;
    }

    public FishingInstructor() {
    }

    public FishingInstructor(ProviderRegistration providerRegistration) {
        super(providerRegistration);
    }
}
