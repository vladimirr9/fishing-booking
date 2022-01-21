package com.project.fishingbookingback.dto.request;


import javax.validation.constraints.NotBlank;

public class ComplaintAnswerDTO {
    @NotBlank
    private String message;

    public ComplaintAnswerDTO() {
    }

    public String getMessage() {
        return message;
    }
}
