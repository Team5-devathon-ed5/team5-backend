package com.team5devathon5.abledappbackend.accounts;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="Account")
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name="Last_Name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    private Phone phone;



    //link al archivo de imagen
    @Column(name="image")
    private String imageLink;

    @Column(unique = true)
    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)" +
                "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Must be a valid email.")
    private String email;

    @Column(name="account_active")
    private Boolean accountActive;

    private String description;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(unique = true)
    private String username;
    // ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\d!@#$%^&*()_+]{8,16}$
    //La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula
    // y al menos una mayúscula. Puede tener otros símbolos.
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d!@#$%^&*()_+]{8,16}$",
            message = "Must contain between 8 and 16 characters with at least one digit, " +
                    "at least a letter in uppercase and a letter in lowercase. Can contain symbols.")
    private String password;

    private String country;

    //se validará de forma diferente
    @Column(name="remember_token")
    private String rememberToken;

    private String city;

    private String address;

    @Column(name="Postal_Code")
    @Pattern(regexp = "^\\d{5,6}$")
    private String postalCode;

    private Role role;

    public Account(String name, String lastName, String imageLink, String email,
                   Boolean accountActive, String description, String username,
                   String password, String country, String rememberToken, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.imageLink = imageLink;
        this.email = email;
        this.accountActive = accountActive;
        this.description = description;
        this.username = username;
        this.password = password;
        this.country = country;
        this.rememberToken = rememberToken;
        this.role = role;
    }
}
