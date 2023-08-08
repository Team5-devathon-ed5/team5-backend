package com.team5devathon5.abledappbackend.Service;

import com.team5devathon5.abledappbackend.User.DataNewUser;
import com.team5devathon5.abledappbackend.accounts.User;
import com.team5devathon5.abledappbackend.accounts.UserRepository;
import com.team5devathon5.abledappbackend.accounts.Role;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegisterService implements UserDetailsService {

    private final UserRepository userRepository;

    public void registerUser(DataNewUser dataNewUser){

        PasswordEncoder passwordBCrypt= new BCryptPasswordEncoder();
        String userHashedPassword = passwordBCrypt.encode(dataNewUser.password());

        User newUser =  new User(null,null,null,null,null,userHashedPassword,dataNewUser.email(),null,
                                            null,null,null,null,null,LocalDateTime.now(),null,null);
        newUser.setRole(Role.LODGER);

        if (userRepository.findByEmail(dataNewUser.email()) != null) {
            throw new DataIntegrityViolationException("email exist");
        }else {
            userRepository.save(newUser);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
