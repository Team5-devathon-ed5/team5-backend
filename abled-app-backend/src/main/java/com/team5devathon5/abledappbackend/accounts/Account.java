package com.team5devathon5.abledappbackend.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name="Last_Name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id", referencedColumnName = "phone_number")
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

    @Column(name="update_at")
    private LocalDateTime updatedAt;

    // ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\d!@#$%^&*()_+]{8,16}$
    //La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula
    // y al menos una mayúscula. Puede tener otros símbolos.
    //@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d!@#$%^&*()_+]{8,16}$",
            //message = "Must contain between 8 and 16 characters with at least one digit, " +
                    //"at least a letter in uppercase and a letter in lowercase. Can contain symbols.")
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

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return authorities;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
