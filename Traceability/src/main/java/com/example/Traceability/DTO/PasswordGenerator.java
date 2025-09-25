package com.example.Traceability.DTO;

import java.security.SecureRandom;

public class PasswordGenerator {
	 
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static final String ALL = UPPER + LOWER + DIGITS + SPECIAL;
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);

        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        for (int i = 4; i < length; i++) {
            password.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        char[] pwdArray = password.toString().toCharArray();
        for (int i = 0; i < pwdArray.length; i++) {
            int j = random.nextInt(pwdArray.length);
            char temp = pwdArray[i];
            pwdArray[i] = pwdArray[j];
            pwdArray[j] = temp;
        }

        return new String(pwdArray);
    }
}
