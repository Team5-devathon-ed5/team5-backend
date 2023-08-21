package com.team5devathon5.abledappbackend.services;

import com.team5devathon5.abledappbackend.domain.Role;
import com.team5devathon5.abledappbackend.domain.RoleGroup;
import com.team5devathon5.abledappbackend.domain.repositories.RoleGroupRepository;
import com.team5devathon5.abledappbackend.dtos.DataNewUser;
import com.team5devathon5.abledappbackend.domain.repositories.RoleRepository;
import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@AllArgsConstructor

public class RegisterService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleGroupRepository roleGroupRepository;

    public void registerUser(DataNewUser dataNewUser){

        PasswordEncoder passwordBCrypt= new BCryptPasswordEncoder();
        String userHashedPassword = passwordBCrypt.encode(dataNewUser.password());

        User newUser =  new User();
        newUser.setPassword(userHashedPassword);
        newUser.setEmail(dataNewUser.email());
        LocalDateTime now = LocalDateTime.now();
        newUser.setCreatedAt(now);
        newUser.setUpdatedAt(now);

        Role roles = roleRepository.findRoleByNameRole("ROLE_LODGER").orElse(null);
        newUser.setRole(Collections.singletonList(roles));

        if (userRepository.existsByEmail(newUser.getEmail())) {
                throw new DataIntegrityViolationException("email exist");
        }else {
            userRepository.save(newUser);
            RoleGroup roleGroup = new RoleGroup(newUser.getId(), 2);
            roleGroupRepository.save(roleGroup);
        }
    }

}
