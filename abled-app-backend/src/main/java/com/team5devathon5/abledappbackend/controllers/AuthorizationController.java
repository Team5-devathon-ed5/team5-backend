package com.team5devathon5.abledappbackend.controllers;
import com.team5devathon5.abledappbackend.infraestructure.exceptions.BadRequestException;
import com.team5devathon5.abledappbackend.infraestructure.messages.ApiResponse;
import com.team5devathon5.abledappbackend.dtos.DataJWTtoken;
import com.team5devathon5.abledappbackend.services.TokenService;
import com.team5devathon5.abledappbackend.services.RegisterService;
import com.team5devathon5.abledappbackend.dtos.DataAuthenticationUser;
import com.team5devathon5.abledappbackend.dtos.DataNewUser;
import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.domain.repositories.UserRepository;
import com.team5devathon5.abledappbackend.utilities.Tables;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ("http://localhost:4200"))
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RegisterService registerService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity <DataJWTtoken> authenticationUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        passwordEncoder.encode(dataAuthenticationUser.password());

        var userFound = userRepository.findByEmail(dataAuthenticationUser.email());

        if (userFound==null) {
            throw new RuntimeException("User not found");
        } else if (!passwordEncoder.matches(dataAuthenticationUser.password(),userFound.getPassword())) {
            throw new BadCredentialsException("Password Incorrect");
        }

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataAuthenticationUser.email(),dataAuthenticationUser.password());

        var userAuthenticate = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) userAuthenticate.getPrincipal());
        return ResponseEntity.ok(new DataJWTtoken(JWTtoken));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser (@RequestBody @Valid DataNewUser dataNewUser){

        if (dataNewUser.email().matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
             String message = "User Registration Successful!. Please Login.";
             ApiResponse response = new ApiResponse(message);

             registerService.registerUser(dataNewUser);

                return ResponseEntity.ok().body(response);
        } else {
            throw new BadRequestException(Tables.users.name());
        }

    }
}
