package com.team5devathon5.abledappbackend.Controller;
import com.team5devathon5.abledappbackend.Infra.Message.ApiResponse;
import com.team5devathon5.abledappbackend.DTO.DataJWTtoken;
import com.team5devathon5.abledappbackend.Service.TokenService;
import com.team5devathon5.abledappbackend.Service.RegisterService;
import com.team5devathon5.abledappbackend.DTO.DataAuthenticationUser;
import com.team5devathon5.abledappbackend.DTO.DataNewUser;
import com.team5devathon5.abledappbackend.accounts.Users;
import com.team5devathon5.abledappbackend.accounts.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        var JWTtoken = tokenService.generateToken((Users) userAuthenticate.getPrincipal());

        return ResponseEntity.ok(new DataJWTtoken(JWTtoken));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody @Valid DataNewUser dataNewUser){

        String message = "User Registration Successful!. Please Login.";
        ApiResponse response = new ApiResponse(message);

        registerService.registerUser(dataNewUser);

        return ResponseEntity.ok().body(response);
    }
}
