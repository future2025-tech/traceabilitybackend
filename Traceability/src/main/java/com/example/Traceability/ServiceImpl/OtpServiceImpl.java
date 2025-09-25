package com.example.Traceability.ServiceImpl;
	
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpServiceImpl {

    private final Map<String, OtpData> otpStorage = new HashMap<>();

    // Generate a 6-digit OTP and store it with 5 min expiry
    public String generateOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStorage.put(email, new OtpData(otp, LocalDateTime.now().plusMinutes(5)));
        return otp;
    }

    // Validate the given OTP
    public boolean validateOtp(String email, String inputOtp) {
        OtpData otpData = otpStorage.get(email);
        if (otpData == null) {
            return false; // nothing stored
        }
        if (otpData.getExpiry().isBefore(LocalDateTime.now())) {
            otpStorage.remove(email); 
            return false;
        }
        return otpData.getOtp().equals(inputOtp); 
    }

    // Inner class to hold OTP and its expiry
    private static class OtpData {
        private final String otp;
        private final LocalDateTime expiry;

        OtpData(String otp, LocalDateTime expiry) {
            this.otp = otp;
            this.expiry = expiry;
        }

        String getOtp() { return otp; }
        LocalDateTime getExpiry() { return expiry; }
    }
}
