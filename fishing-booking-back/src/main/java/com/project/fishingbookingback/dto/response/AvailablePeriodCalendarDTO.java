package com.project.fishingbookingback.dto.response;

import com.project.fishingbookingback.model.AvailablePeriod;

import java.time.LocalDateTime;

public class AvailablePeriodCalendarDTO {
    private Long id;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    private String name;

    public AvailablePeriodCalendarDTO(AvailablePeriod ap) {
        id = ap.getId();
        fromTime = ap.getFromTime();
        toTime = ap.getToTime();
        name = ((ap.getBoat() == null) ? ap.getHolidayHome().getName() : ap.getBoat().getName());
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public String getName() {
        return name;
    }
}
