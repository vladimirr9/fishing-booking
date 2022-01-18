package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("BOAT_OWNER")
public class BoatOwner extends User {
    public BoatOwner() {
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boatOwner")
    @JsonBackReference
    private List<Boat> boats;

    public BoatOwner(ProviderRegistration providerRegistration) {
        super(providerRegistration);
    }
}
