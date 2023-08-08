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
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    @Column(name = "phone_code")
    private String phoneCode;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "phone_share")
    private Boolean phoneShare;

    private String password;

    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)" +
            "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Must be a valid email.")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "user_active")
    private Boolean userActive;

    private String detail;

    private String address;

    private String Country;

    @Column(name = "remember_token")
    private String rememberToken;

    private LocalDateTime created;

    private LocalDateTime updated;


    // ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\d!@#$%^&*()_+]{8,16}$
    //La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula
    // y al menos una mayúscula. Puede tener otros símbolos.
    //@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d!@#$%^&*()_+]{8,16}$",
            //message = "Must contain between 8 and 16 characters with at least one digit, " +
                    //"at least a letter in uppercase and a letter in lowercase. Can contain symbols.")

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
