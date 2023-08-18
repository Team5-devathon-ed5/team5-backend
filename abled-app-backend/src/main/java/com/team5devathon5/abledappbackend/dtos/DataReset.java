package com.team5devathon5.abledappbackend.dtos;

import jakarta.validation.constraints.NotBlank;


public record DataReset(
                        @NotBlank
                        String email,
                        @NotBlank
                        String opt
) {
}
