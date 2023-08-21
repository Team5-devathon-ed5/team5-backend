package com.team5devathon5.abledappbackend.services;
import com.team5devathon5.abledappbackend.domain.UserRepository;
import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.infraestructure.messages.EmailData;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
@Service
@AllArgsConstructor
public class EmailService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    public void sendEmail(String email){

       RestTemplate restTemplate = new RestTemplate();

       HttpHeaders headers = new HttpHeaders();

       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE2OTE2NTkyMzEsImV4cCI6NDg0NzMzMjgzMSwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJraWQiOiI2NGQ0YWJkZmVjMWI5YTAyMGQwZjEwZjQiLCJhaWQiOiI2NGNiZDYzNWQzZDQzOTFlNzQwZGU3MzkiLCJ1c2VybmFtZSI6ImRldmF0aG9uZGV2c0BnbWFpbC5jb20ifQ.PIUKorflXnVi_wW7xJ3Bj8qaH9frz5VtQyz6NN_nmqAtZs4XUGly-7hDhyZcBE2wV6CW8BMZVRBoDdpzuufvuNL3n5OYqaB63_emw9ltHMbZFINaoj6KxOzH0dXRmw_bw_6yl173yXGkIkEtiIDRNkR2_GDK7ECMz8MgnhEotPhtc57mO0ypMXwWONulYSn1o4M7wRjeVywFHT3Clsy4mYCpNKdemlxs5vDcDYnWKeOCt2q0VO2PYwBiDoka-G4tkkDDWnJgDQinDymtIHNHWN5GAv0Ed99s5hOrMX25nmfKNIDxnYEujHm_2ab5PECkBcfbpEmw5Hfpa6-8w0twaw");

       User forgotUser = userRepository.findByEmail(email);
       String token = tokenService.generateResetPassword(forgotUser);
       forgotUser.setForgotPassword(token);
       userRepository.save(forgotUser);

       String resetLink ="http://localhost:8080/api/v1/password/reset/"+token;
       EmailData emailData =  new EmailData();

       emailData.setFrom("ableb_support@programacion-es.dev");
       emailData.setTo(email);
       emailData.setSubject("Password reset");
       emailData.setHtml("Hi,\n\n" +
                "Forgot your password?\n" +
                "We received a request to reset the password for your account.\n\n" +
                "To reset your password, click on the following button:\n\n" +
                "\n\n" +
                "\n\n" +
                "<a href=\"" + resetLink + "\" style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px;\">Reset Password</a>");

        HttpEntity<EmailData> request = new HttpEntity<>(emailData,headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://api.envialosimple.email/api/v1/mail/send",
                request,String.class);
    }

}
