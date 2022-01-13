package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.NotBlank;

public class ReportRequestDTO {
    @NotBlank
    private String comment;
    private boolean sanction;
    private boolean appeared;

    public ReportRequestDTO() {
    }

    public String getComment() {
        return comment;
    }

    public boolean isSanction() {
        return sanction;
    }

    public boolean isAppeared() {
        return appeared;
    }
}
