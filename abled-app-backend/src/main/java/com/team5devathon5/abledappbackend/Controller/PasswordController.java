package com.team5devathon5.abledappbackend.Controller;

import com.team5devathon5.abledappbackend.Infra.Message.ApiResponse;
import com.team5devathon5.abledappbackend.Service.OptService;
import com.team5devathon5.abledappbackend.User.DataForgot;
import com.team5devathon5.abledappbackend.User.DataReset;
import com.team5devathon5.abledappbackend.accounts.UserRepository;
import com.team5devathon5.abledappbackend.accounts.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {

    private final UserRepository userRepository;
    private final OptService optService;
    private final UserService userService;


    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse> forgotPassword (@RequestBody @Valid DataForgot dataForgot){
        var userExist = userRepository.findByEmail(dataForgot.email());
        System.out.println(userExist);
        if (userExist !=null){
            String opt = optService.generateOPT();
            userExist.setRememberToken(opt);
            userService.updateUserByEmail(userExist.getEmail(),userExist);

            System.out.println(opt);
        }else{
            throw new RuntimeException("User not found");
        }

        return ResponseEntity.ok().body(new ApiResponse("Check your email: " + dataForgot.email() + ", inbox or spam. Able your best option."));
    }

    @PostMapping("/reset")
    public ResponseEntity<ApiResponse> resetPassword (@RequestBody @Valid DataReset dataReset){
        var userReset = userRepository.findByEmail(dataReset.email());
        System.out.println(dataReset.opt());
        boolean validOpt = optService.validateOtp(dataReset.opt(),userReset.getRememberToken());
        if (!validOpt) {
            throw new RuntimeException("validOpt expired");
        }
        userReset.setRememberToken(null);
        userService.updateUserByEmail(userReset.getEmail(),userReset);

        return ResponseEntity.ok().body(new ApiResponse("Password Reset"));
    }
}
