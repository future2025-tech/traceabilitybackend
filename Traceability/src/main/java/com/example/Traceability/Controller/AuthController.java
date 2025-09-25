package com.example.Traceability.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.Traceability.DTO.EmailRequest;
import com.example.Traceability.DTO.JwtResponse;
import com.example.Traceability.DTO.LoginDTO;
import com.example.Traceability.DTO.PasswordValidator;
import com.example.Traceability.DTO.ResetPassword;
import com.example.Traceability.Entity.LoginEntity;
import com.example.Traceability.Repository.LoginRepository;
import com.example.Traceability.ServiceImpl.EmailServiceImpl;
import com.example.Traceability.ServiceImpl.LoginSerivceImpl;
import com.example.Traceability.ServiceImpl.OtpServiceImpl;
import com.example.Traceability.Util.JwtUtil;

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
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil; 
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody EmailRequest req) {
        userService.createUser(req.getEmail());
        return ResponseEntity.ok("User created successfully. Password sent via email.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
    	
        LoginEntity user = userRepo.findByEmail(loginDTO.getEmail());

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        if (!encoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
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
            return "Password must contain 8+ chars, uppercase, lowercase, "
            		+ "number, special char";

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

    @GetMapping("/all-users")
    public ResponseEntity<List<LoginDTO>> getAllUsers() {
    	
        List<LoginDTO> users = userRepo.findAll().stream()
                .map(user -> {
                    LoginDTO dto = new LoginDTO();
                    dto.setEmail(user.getEmail());
                    dto.setUserRole(user.getUserRole());
                    return dto;
                })
                .toList();
        
        return ResponseEntity.ok(users);
    }
}
