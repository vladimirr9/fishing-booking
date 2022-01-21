package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
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

    @ManyToMany()
    @JsonBackReference
    private List<HolidayHome> subscribedHomes;
    @ManyToMany
    @JsonBackReference
    private List<Boat> subscribedBoats;
    @ManyToMany
    @JsonBackReference
    private List<FishingAdventure> subscribedAdventures;

    public Client(ProviderRegistration providerRegistration) {
        super(providerRegistration);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<HolidayHome> getSubscribedHomes() {
        return subscribedHomes;
    }

    public void setSubscribedHomes(List<HolidayHome> subscribedHomes) {
        this.subscribedHomes = subscribedHomes;
    }

    public List<Boat> getSubscribedBoats() {
        return subscribedBoats;
    }

    public void setSubscribedBoats(List<Boat> subscribedBoats) {
        this.subscribedBoats = subscribedBoats;
    }

    public List<FishingAdventure> getSubscribedAdventures() {
        return subscribedAdventures;
    }

    public void setSubscribedAdventures(List<FishingAdventure> subscribedAdventures) {
        this.subscribedAdventures = subscribedAdventures;
    }
}
