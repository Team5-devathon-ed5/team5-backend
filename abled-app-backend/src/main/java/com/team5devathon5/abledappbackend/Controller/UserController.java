package com.team5devathon5.abledappbackend.Controller;

import com.team5devathon5.abledappbackend.Service.DatosJWTtoken;
import com.team5devathon5.abledappbackend.Service.TokenService;
import com.team5devathon5.abledappbackend.Service.UserService;
import com.team5devathon5.abledappbackend.User.DataAuthenticationUser;
import com.team5devathon5.abledappbackend.User.DataNewUser;
import com.team5devathon5.abledappbackend.User.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/able")
public class  UserController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    public UserController(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService= userService;
    }
    @PostMapping("/login")
    public ResponseEntity <DatosJWTtoken> authenticationUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser){

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataAuthenticationUser.username(),dataAuthenticationUser.password());

        var userAuthenticate = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generateToken((User) userAuthenticate.getPrincipal());

        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Object> registerUser(@RequestBody @Valid DataNewUser dataNewUser){

        String message = "User Registration Successful!. Please Login.";

        userService.registerUser(dataNewUser);

        return ResponseEntity.ok().body(Map.of(
                "message", message
        ));
    }
}
