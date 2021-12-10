package com.project.fishingbookingback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ServiceFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Double fee;

    public ServiceFee() {
    }

    public ServiceFee(Double fee) {
        this.id = 1L;
        this.fee = fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

    public Double getFee() {
        return fee;
    }
}
