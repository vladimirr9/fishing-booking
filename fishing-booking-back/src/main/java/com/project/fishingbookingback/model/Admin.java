package com.project.fishingbookingback.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    public boolean isFirstLogin() {
        return firstLogin;
    }

    private boolean firstLogin;

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Admin() {
    }
}
