package com.team5devathon5.abledappbackend.Controller;
import com.team5devathon5.abledappbackend.Infra.Message.ApiResponse;
import com.team5devathon5.abledappbackend.Service.DatosJWTtoken;
import com.team5devathon5.abledappbackend.Service.TokenService;
import com.team5devathon5.abledappbackend.Service.RegisterService;
import com.team5devathon5.abledappbackend.User.DataAuthenticationAccount;
import com.team5devathon5.abledappbackend.User.DataNewAccount;
import com.team5devathon5.abledappbackend.accounts.Account;
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

    @PostMapping("/login")
    public ResponseEntity <DatosJWTtoken> authenticationUser(@RequestBody @Valid DataAuthenticationAccount dataAuthenticationAccount){

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataAuthenticationAccount.email(), dataAuthenticationAccount.password());

        var accountAuthenticate = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generateToken((Account) accountAuthenticate.getPrincipal());

        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody @Valid DataNewAccount dataNewAccount){

        String message = "User Registration Successful!. Please Login.";
        ApiResponse response = new ApiResponse(message);

        registerService.registerAccount(dataNewAccount);

        return ResponseEntity.ok().body(response);
    }
}
