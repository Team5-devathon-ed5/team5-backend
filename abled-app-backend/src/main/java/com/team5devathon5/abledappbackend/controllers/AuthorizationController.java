package com.team5devathon5.abledappbackend.controllers;
import com.team5devathon5.abledappbackend.infra.messages.ApiResponse;
import com.team5devathon5.abledappbackend.dto.DataJWTtoken;
import com.team5devathon5.abledappbackend.services.TokenService;
import com.team5devathon5.abledappbackend.services.RegisterService;
import com.team5devathon5.abledappbackend.dto.DataAuthenticationUser;
import com.team5devathon5.abledappbackend.dto.DataNewUser;
import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.domain.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

        var userFound = userRepository.findByEmail(dataAuthenticationUser.email());
        System.out.println(userFound);
        if (userFound==null) {
            throw new RuntimeException("User not found");
        }

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataAuthenticationUser.email(),dataAuthenticationUser.password());
         System.out.println(authToken);
       var userAuthenticate = authenticationManager.authenticate(authToken);

       var JWTtoken = tokenService.generateToken((User) userAuthenticate.getPrincipal());

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
