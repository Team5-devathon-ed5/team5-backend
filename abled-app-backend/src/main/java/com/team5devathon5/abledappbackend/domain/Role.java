package com.team5devathon5.abledappbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_role")
    private String nameRole;
    private String detail;

    @JsonIgnore
    @ManyToMany (mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users;

    public Role (String nameRole, String detail){
        this.nameRole = nameRole;
        this.detail = detail;
    }
}
