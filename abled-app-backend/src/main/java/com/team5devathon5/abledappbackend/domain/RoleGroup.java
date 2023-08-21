package com.team5devathon5.abledappbackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private Integer idUser;
    private Integer idRole;

    public RoleGroup (Integer idUser, Integer idRole){
        this.idUser = idUser;
        this.idRole = idRole;
    }
}
