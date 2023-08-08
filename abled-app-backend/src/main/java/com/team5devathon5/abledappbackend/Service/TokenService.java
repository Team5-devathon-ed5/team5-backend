package com.team5devathon5.abledappbackend.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.team5devathon5.abledappbackend.accounts.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${able.security.secret}")
    private String ableSecret;
    public String generateToken(User user){

        try {
            Algorithm algorithm = Algorithm.HMAC256(ableSecret);
            return JWT.create()
                    .withIssuer("able")
                    .withSubject(user.getEmail())
                    .withClaim("id",user.getId())
                    .withExpiresAt(tokenExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
    public String getSubject(String token) {

        if(token==null){
            throw new RuntimeException();
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(ableSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("able")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if(verifier.getSubject()==null){
            throw new RuntimeException("Verifier invalid");
        }
        return verifier.getSubject();
    }
    private Instant tokenExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
