package com.example.Traceability.Service;

public interface EmailService {

	public void sendWelcomeEmail(String to, String tempPassword);
	
	public void sendOtpEmail(String to, String otp);
	
	public void sendPasswordChangedEmail(String to);
	
	public void sendAccountDeletedEmail(String to);
}
