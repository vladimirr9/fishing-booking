package com.project.fishingbookingback.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    public Client() {}

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
