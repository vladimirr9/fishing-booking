package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

public class AvailablePeriodDTO {
    LocalDateTime from;
    LocalDateTime to;
    @Email
    String email;

    public AvailablePeriodDTO() {
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public String getEmail() {
        return email;
    }
}
