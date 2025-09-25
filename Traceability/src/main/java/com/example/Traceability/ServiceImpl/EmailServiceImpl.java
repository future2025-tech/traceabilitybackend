package com.example.Traceability.ServiceImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Traceability.Service.EmailService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServiceImpl  implements EmailService{
	
    private final JavaMailSender mailSender;

    public void sendWelcomeEmail(String to, String tempPassword) {
    	
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Account Created Successfully...!");
        msg.setText("Welcome! Your account is created.\nEmail: "
        + to + "\nTemp Password: " + tempPassword + 
                    "\nPlease reset your password.");
        
        mailSender.send(msg);
    }

    public void sendOtpEmail(String to, String otp) {
    	
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Password Reset OTP");
        msg.setText("Your OTP is: " + otp + " (valid for 5 minutes).");
        mailSender.send(msg);
    }

    public void sendPasswordChangedEmail(String to) {
    	
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Password Changed Successfully");
        msg.setText("Your password has been updated successfully.");
        mailSender.send(msg);
    }
    
    public void sendAccountDeletedEmail(String to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Account Deleted");
        msg.setText("Your account associated with " + to +
        		" has been successfully deleted.");
        mailSender.send(msg);
    }

}

