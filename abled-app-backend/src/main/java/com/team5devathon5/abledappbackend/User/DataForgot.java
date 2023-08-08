package com.team5devathon5.abledappbackend.User;

import jakarta.validation.constraints.NotBlank;

public record DataForgot(
                            @NotBlank
                            String email) {
}
