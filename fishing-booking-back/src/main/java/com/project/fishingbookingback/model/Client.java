package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Reservation> reservations;

    public Client() {
    }

    private boolean isEnabled;

    public Client(ProviderRegistration providerRegistration) {
        super(providerRegistration);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
