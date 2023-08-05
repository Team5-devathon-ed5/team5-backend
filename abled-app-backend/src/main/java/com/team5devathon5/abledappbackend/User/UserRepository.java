package com.team5devathon5.abledappbackend.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
<<<<<<< HEAD

public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByUsername(String username);
=======
public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByUsername(String username);
    User findByEmail(String email);
>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
}
