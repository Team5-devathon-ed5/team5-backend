package com.team5devathon5.abledappbackend.User;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataNewUser(@NotBlank
                          String username,
                          @NotBlank
                          String password,
                          @Nullable
                          String name,
                          @Nullable
                          String surname,
                          @NotBlank
                          @Email
                          String email) {
}
