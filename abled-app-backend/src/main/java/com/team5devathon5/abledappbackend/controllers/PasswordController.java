package com.team5devathon5.abledappbackend.controllers;
import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.dtos.DataForgot;
import com.team5devathon5.abledappbackend.dtos.DataResetUser;
import com.team5devathon5.abledappbackend.infraestructure.messages.ApiResponse;
import com.team5devathon5.abledappbackend.services.EmailService;
import com.team5devathon5.abledappbackend.services.TokenService;
import jakarta.validation.Valid;
import com.team5devathon5.abledappbackend.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenService tokenService;

    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse>forgotPassword(@RequestBody @Valid DataForgot dataForgot){

        boolean existEmail = userRepository.existsByEmail(dataForgot.email());
        if(!existEmail) {
        throw new RuntimeException("User not found");
        }
        emailService.sendEmail(dataForgot.email());
        return ResponseEntity.ok().body(new ApiResponse("Check your email: " + dataForgot.email() + ", inbox or spam. Able your best option."));
    }

    @PostMapping("/reset/{token}")
    public ResponseEntity<ApiResponse>resetPassword(@RequestBody @Valid DataResetUser dataResetUser, @PathVariable String token){

        String subjectReset = tokenService.getSubjectReset(token);
        User updateUser = userRepository.findByEmail(subjectReset);

        if (updateUser.getForgotPassword()==null) {
            return ResponseEntity.status(403).body(new ApiResponse("Token used"));
        } else {
            PasswordEncoder passwordBCrypt= new BCryptPasswordEncoder();
            String hashPasswordUpdate = passwordBCrypt.encode(dataResetUser.newPassword());
            updateUser.setPassword(hashPasswordUpdate);
            updateUser.setForgotPassword(null);
            userRepository.save(updateUser);
            return ResponseEntity.ok().body(new ApiResponse("Successful, please login."));
        }
    }

}
