package com.team5devathon5.abledappbackend.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataNewUser(@NotBlank
                          String username,
                          @NotBlank
                          String password,
                          @NotBlank
                          String name,
                          @NotBlank
                          String surname,
                          @NotBlank
                          @Email
                          String email) {
}
