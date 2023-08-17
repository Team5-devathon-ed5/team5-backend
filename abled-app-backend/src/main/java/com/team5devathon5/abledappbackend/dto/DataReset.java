package com.team5devathon5.abledappbackend.dto;

import jakarta.validation.constraints.NotBlank;


public record DataReset(
                        @NotBlank
                        String email,
                        @NotBlank
                        String opt
) {
}
