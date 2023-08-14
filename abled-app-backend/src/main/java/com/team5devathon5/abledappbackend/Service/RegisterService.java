package com.team5devathon5.abledappbackend.Service;

import com.team5devathon5.abledappbackend.DTO.DataNewUser;
import com.team5devathon5.abledappbackend.accounts.*;
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

    public void registerUser(DataNewUser dataNewUser){

        PasswordEncoder passwordBCrypt= new BCryptPasswordEncoder();
        String userHashedPassword = passwordBCrypt.encode(dataNewUser.password());

        Users newUser =  new Users(null,null,null,null,null,userHashedPassword,dataNewUser.email(),null,
                null,null,null,null,null,LocalDateTime.now(),null,null);

        Role roles = roleRepository.findRoleByNameRole("ROLE_LODGER").orElse(null);
        newUser.setRole(Collections.singletonList(roles));


        if (userRepository.existsByEmail(newUser.getEmail())) {
                throw new DataIntegrityViolationException("email exist");
        }else {
            userRepository.save(newUser);
        }
    }

}
