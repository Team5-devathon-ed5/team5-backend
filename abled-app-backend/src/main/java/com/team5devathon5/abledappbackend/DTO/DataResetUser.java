package com.team5devathon5.abledappbackend.DTO;

import jakarta.validation.constraints.NotBlank;

public record DataResetUser(
        @NotBlank
        String newPassword) {
}
