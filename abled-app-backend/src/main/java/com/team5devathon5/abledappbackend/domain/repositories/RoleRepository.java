package com.team5devathon5.abledappbackend.domain.repositories;

import com.team5devathon5.abledappbackend.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByNameRole (String nameRole);
}
