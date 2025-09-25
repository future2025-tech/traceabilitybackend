package com.example.Traceability.Service;

public interface OtpService {

	public String generateOtp(String email);
	
	public boolean validateOtp(String email, String otp);
}
