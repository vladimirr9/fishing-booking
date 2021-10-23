package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.NotBlank;

public class ProviderRegistrationDeniedRequestDTO {
    public ProviderRegistrationDeniedRequestDTO() {
    }

    public String getMessage() {
        return message;
    }

    @NotBlank(message = "Reason for denial must not be blank")
    String message;
}
