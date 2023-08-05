package com.team5devathon5.abledappbackend.Service;

import com.team5devathon5.abledappbackend.User.DataNewUser;
import com.team5devathon5.abledappbackend.User.User;
import com.team5devathon5.abledappbackend.User.UserRepository;
<<<<<<< HEAD
=======
import org.springframework.dao.DataIntegrityViolationException;
>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
<<<<<<< HEAD
=======

        UserDetails user = userRepository.findByUsername(username);

>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
        return userRepository.findByUsername(username);
    }

    public void registerUser (DataNewUser dataNewUser){

        PasswordEncoder passwordBCrypt= new BCryptPasswordEncoder();
        String userHashedPassword = passwordBCrypt.encode(dataNewUser.password());


<<<<<<< HEAD
        User newUser =  new User(null, dataNewUser.name(),userHashedPassword,dataNewUser.name()
                                ,dataNewUser.surname(), dataNewUser.email());

         userRepository.save(newUser);
=======
        User newUser =  new User(null, dataNewUser.username(),userHashedPassword,dataNewUser.name()
                                ,dataNewUser.surname(), dataNewUser.email());

        if (userRepository.findByUsername(dataNewUser.username()) != null) {
            throw new DataIntegrityViolationException("username exist");
           // throw new RuntimeException("User is already register_user service");
        }else if (userRepository.findByEmail(dataNewUser.email()) != null){
            throw new DataIntegrityViolationException("email exist");
        }
        else {
            userRepository.save(newUser);
        }
>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
    }
}
