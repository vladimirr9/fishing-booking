package com.project.fishingbookingback.dto.request;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequstDTO {
    public ChangePasswordRequstDTO() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @NotBlank
    private String currentPassword;
    @NotBlank
    private String newPassword;
}
