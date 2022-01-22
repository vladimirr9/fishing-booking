package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateReservationDTO {
    @Min(value = 1)
    double price;
    @NotNull
    LocalDateTime from;
    @NotNull
    LocalDateTime to;
    @NotBlank
    String clientUsername;
    @NotNull
    Long entityId;
    @NotBlank
    String type;

    Long[] additionalServicesIds;

    public Long[] getAdditionalServicesIds() {
        return additionalServicesIds;
    }

    public void setAdditionalServicesIds(Long[] additionalServicesIds) {
        this.additionalServicesIds = additionalServicesIds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
