package com.example.Traceability.Service;

public interface loginService {
	
	public void updatePassword(String email, String newPassword);
	
    public boolean isPasswordUsedBefore(String email, String newPassword);
    
    public void deleteUser(String email);
}
