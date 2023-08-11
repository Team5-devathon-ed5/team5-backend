package com.team5devathon5.abledappbackend.accounts;

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

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Users implements UserDetails{

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_group",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id")
    )
    private List<Role> role= new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNameRole()));
        }
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
