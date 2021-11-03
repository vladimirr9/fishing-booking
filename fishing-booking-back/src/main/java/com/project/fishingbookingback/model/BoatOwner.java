package com.project.fishingbookingback.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BOAT_OWNER")
public class BoatOwner extends User {
    public BoatOwner() {
    }
}
