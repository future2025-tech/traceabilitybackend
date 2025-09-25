package com.example.Traceability.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Traceability.DTO.EmailRequest;
import com.example.Traceability.DTO.LoginRequest;
import com.example.Traceability.DTO.PasswordValidator;
import com.example.Traceability.DTO.ResetPassword;
import com.example.Traceability.Entity.LoginEntity;
import com.example.Traceability.Repository.LoginRepository;
import com.example.Traceability.ServiceImpl.EmailServiceImpl;
import com.example.Traceability.ServiceImpl.LoginSerivceImpl;
import com.example.Traceability.ServiceImpl.OtpServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final OtpServiceImpl otpService;
    private final EmailServiceImpl emailService;
    private final LoginSerivceImpl userService;
    private final LoginRepository userRepo;
   

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody LoginRequest req) {
        try {
            LoginEntity user = userService.createUser(req.getEmail(), req.getPassword());
            try {
                emailService.sendWelcomeEmail(user.getEmail(), req.getPassword());
            } catch (Exception e) {
                System.err.println("Email send failed: " + e.getMessage());
            }
            return ResponseEntity.ok("User created successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Create user failed: "
        + ex.getMessage());
        }
    }


    @PostMapping("/forgot-password")
    public String requestOtp(@RequestBody EmailRequest emailRequest) {
    	
        String email = emailRequest.getEmail();

        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);

        return "OTP sent to email";
    }


    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPassword resetPassword) {

        if (!resetPassword.getNewPassword().equals(resetPassword.getConfirmPassword()))
            return "Passwords do not match";

        if (!PasswordValidator.isValid(resetPassword.getNewPassword()))
            return "Password must contain 8+ chars, uppercase, "
            		+ "lowercase, number, special char";

        if (!otpService.validateOtp(resetPassword.getEmail(), resetPassword.getOtp()))
            return "Invalid or expired OTP";

        if (userService.isPasswordUsedBefore(resetPassword.getEmail(), 
        		resetPassword.getNewPassword()))
            return "Cannot reuse previous password";

        userService.updatePassword(resetPassword.getEmail(), 
        		resetPassword.getNewPassword());
        
        emailService.sendPasswordChangedEmail(resetPassword.getEmail());

        return "Password updated successfully";
    }
    
    @DeleteMapping("/delete-account")
    public String deleteAccount(@RequestBody EmailRequest emailRequest) {
    	
        if (userRepo.findByEmail(emailRequest.getEmail()) == null) {
            return "User not found";
        }

        userService.deleteUser(emailRequest.getEmail());
        emailService.sendAccountDeletedEmail(emailRequest.getEmail());

        return "User account deleted successfully";
    }

}
