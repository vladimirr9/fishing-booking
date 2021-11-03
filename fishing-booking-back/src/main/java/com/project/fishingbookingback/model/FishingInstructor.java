package com.project.fishingbookingback.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FISHING_INSTRUCTOR")
public class FishingInstructor extends User {
    public FishingInstructor() {
    }

    public FishingInstructor(ProviderRegistration providerRegistration) {
        super(providerRegistration);
    }
}
