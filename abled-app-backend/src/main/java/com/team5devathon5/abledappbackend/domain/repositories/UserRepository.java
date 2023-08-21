package com.team5devathon5.abledappbackend.domain.repositories;

import com.team5devathon5.abledappbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail (String email);
    Boolean existsByEmail (String email);
}
