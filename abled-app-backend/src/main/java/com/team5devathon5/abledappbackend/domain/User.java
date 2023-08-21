package com.team5devathon5.abledappbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Pattern(regexp = "^\\+[1-9]\\d{0,2}",
            message = "Must be a valid international Phone code preceded by '+'.")
    private String phoneCode;

    @Pattern(regexp = "^\\d{1,4}-\\d{1,4}-\\d{1,10}$",
            message = "Must be a valid phone number.")
    private String phoneNumber;

    private Boolean phoneShare;

    @JsonIgnore
    private String password;

    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)" +
            "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Must be a valid email.")
    private String email;

    private String imageUrl;

    private Boolean userActive;

    private String detail;

    private String address;

    private String country;

    private String forgotPassword;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "roleGroup",
            joinColumns = @JoinColumn(name = "idUser", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idRole", referencedColumnName = "id")
    )
    //@Column(insertable=false, updatable=false)
    private List<Role> role = new ArrayList<>();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNameRole()));
        }
        return authorities;
    }

    @Override
    @JsonIgnore
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
