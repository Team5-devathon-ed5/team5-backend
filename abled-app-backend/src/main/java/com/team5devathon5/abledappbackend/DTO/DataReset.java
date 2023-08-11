package com.team5devathon5.abledappbackend.DTO;

import jakarta.validation.constraints.NotBlank;


public record DataReset(
                        @NotBlank
                        String email,
                        @NotBlank
                        String opt
) {
}
