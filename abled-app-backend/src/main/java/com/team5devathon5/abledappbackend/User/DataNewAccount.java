package com.team5devathon5.abledappbackend.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataNewAccount(
                            @NotBlank
                            @Email
                            String email,
                            @NotNull
                            String password,
                            @NotBlank
                            String name,
                            @NotBlank
                            String last_name,
                            @NotBlank
                            String country) {
}
