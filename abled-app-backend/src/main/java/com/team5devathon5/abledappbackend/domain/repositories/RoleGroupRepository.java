package com.team5devathon5.abledappbackend.domain.repositories;

import com.team5devathon5.abledappbackend.domain.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Integer> {
}
