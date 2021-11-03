package com.project.fishingbookingback.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HOME_OWNER")
public class HomeOwner extends User {
    public HomeOwner() {
    }

    public HomeOwner(ProviderRegistration providerRegistration) {
        super(providerRegistration);
    }
}
