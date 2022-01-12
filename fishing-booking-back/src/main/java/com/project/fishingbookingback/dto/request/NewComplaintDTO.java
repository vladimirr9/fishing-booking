package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewComplaintDTO {
    @NotNull
    private long reservationId;
    @NotBlank
    private String content;

    public NewComplaintDTO() {
    }

    public long getReservationId() {
        return reservationId;
    }

    public String getContent() {
        return content;
    }
}
