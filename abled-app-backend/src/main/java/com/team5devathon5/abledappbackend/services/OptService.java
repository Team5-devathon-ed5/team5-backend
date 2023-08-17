package com.team5devathon5.abledappbackend.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OptService {

    private static final String OTP_CHARACTERS_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String OTP_CHARACTERS_NUMBERS = "0123456789";
    private static final String OTP_CHARACTERS_SYMBOLS = "!@#$%^&*_=+-/.?<>)";

    private static final String OTP_CHARACTERS= OTP_CHARACTERS_LETTER + OTP_CHARACTERS_NUMBERS + OTP_CHARACTERS_SYMBOLS;
    private static final int OTP_LENGTH = 12;

    public String generateOPT(){

        StringBuilder otp = new StringBuilder();

        Random random =  new Random();
        for (int i=0; i < OTP_LENGTH; i++){
            int randomIndex= random.nextInt(OTP_CHARACTERS.length());
            otp.append(OTP_CHARACTERS.charAt(randomIndex));
        }

        return otp.toString();
    }

    public boolean validateOtp(String accountInputOtp, String storedOtp){
        return accountInputOtp.equals(storedOtp);
    }

}
