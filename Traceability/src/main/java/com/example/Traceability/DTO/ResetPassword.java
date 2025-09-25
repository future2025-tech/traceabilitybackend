package com.example.Traceability.DTO;

import lombok.Data;

@Data
public class ResetPassword {
	
	String email;
	
    String otp;
    
    String newPassword;
    
    String confirmPassword;
}
