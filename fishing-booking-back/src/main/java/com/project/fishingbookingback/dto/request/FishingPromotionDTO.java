package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class FishingPromotionDTO {
    @NotNull
    private LocalDateTime fromTime;
    @NotNull
    private LocalDateTime toTime;
    @NotNull
    private Double price;
    @NotNull
    private LocalDateTime validUntil;

    private int peopleNumber;

    public FishingPromotionDTO() {
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }
}
