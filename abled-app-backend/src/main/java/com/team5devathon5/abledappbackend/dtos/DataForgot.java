package com.team5devathon5.abledappbackend.dtos;

import jakarta.validation.constraints.NotBlank;

public record DataForgot(
                            @NotBlank
                            String email) {
}
