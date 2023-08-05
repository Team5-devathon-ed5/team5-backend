package com.team5devathon5.abledappbackend.User;

<<<<<<< HEAD
=======
import jakarta.annotation.Nullable;
>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataNewUser(@NotBlank
                          String username,
                          @NotBlank
                          String password,
<<<<<<< HEAD
                          @NotBlank
                          String name,
                          @NotBlank
=======
                          @Nullable
                          String name,
                          @Nullable
>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
                          String surname,
                          @NotBlank
                          @Email
                          String email) {
}
