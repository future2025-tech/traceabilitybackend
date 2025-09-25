package com.example.Traceability.DTO;

import java.util.regex.Pattern;

public class PasswordValidator {

	 private static final String PASSWORD_PATTERN =
	            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";

	    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	    public static boolean isValid(String password) {
	       
	    	return pattern.matcher(password).matches();
	    }
}
