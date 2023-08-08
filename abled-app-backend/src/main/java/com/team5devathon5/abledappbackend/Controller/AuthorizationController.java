package com.team5devathon5.abledappbackend.Controller;
import com.team5devathon5.abledappbackend.Infra.Message.ApiResponse;
import com.team5devathon5.abledappbackend.Service.DataJWTtoken;
import com.team5devathon5.abledappbackend.Service.TokenService;
import com.team5devathon5.abledappbackend.Service.RegisterService;
import com.team5devathon5.abledappbackend.User.DataAuthenticationUser;
import com.team5devathon5.abledappbackend.User.DataNewUser;
import com.team5devathon5.abledappbackend.accounts.User;
import com.team5devathon5.abledappbackend.accounts.UserRepository;
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
        if (userFound == null) {
            throw new RuntimeException("User not found");
        }
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataAuthenticationUser.email(), dataAuthenticationUser.password());

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
