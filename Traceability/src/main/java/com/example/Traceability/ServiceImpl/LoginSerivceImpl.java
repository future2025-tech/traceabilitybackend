package com.example.Traceability.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Traceability.DTO.PasswordGenerator;
import com.example.Traceability.Entity.LoginEntity;
import com.example.Traceability.Entity.PasswordHistory;
import com.example.Traceability.Repository.LoginRepository;
import com.example.Traceability.Repository.PasswordHistoryRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginSerivceImpl implements UserDetailsService {

    private final LoginRepository loginRepo;
    private final PasswordHistoryRepository historyRepo;
    private final BCryptPasswordEncoder encoder;
    private final EmailServiceImpl emailService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        LoginEntity user = loginRepo.findByEmail(email);
        if (user == null)
            throw new RuntimeException("User not found with email: " + email);

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Transactional
    public LoginEntity createUser(String email) {
        if (loginRepo.findByEmail(email) != null)
            throw new RuntimeException("User already exists");

        String rawPassword = PasswordGenerator.generatePassword(12);
        String hashedPassword = encoder.encode(rawPassword);

        LoginEntity user = new LoginEntity();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        loginRepo.save(user);

        PasswordHistory ph = new PasswordHistory();
        ph.setEmail(email);
        ph.setPassword(hashedPassword);
        ph.setChangedAt(LocalDateTime.now());
        historyRepo.save(ph);

        emailService.sendWelcomeEmail(email, rawPassword); // send raw password via email
        return user;
    }

    public boolean isPasswordUsedBefore(String email, String newPassword) {
        List<PasswordHistory> history = historyRepo.findTop3ByEmailOrderByChangedAtDesc(email);
        for (PasswordHistory ph : history) {
            if (encoder.matches(newPassword, ph.getPassword()))
                return true;
        }
        return false;
    }

    public void updatePassword(String email, String newPassword) {
        LoginEntity user = loginRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        String hashed = encoder.encode(newPassword);
        user.setPassword(hashed);
        loginRepo.save(user);

        PasswordHistory ph = new PasswordHistory();
        ph.setEmail(email);
        ph.setPassword(hashed);
        ph.setChangedAt(LocalDateTime.now());
        historyRepo.save(ph);
    }

    public void deleteUser(String email) {
        LoginEntity user = loginRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        List<PasswordHistory> historyList = historyRepo.findTop3ByEmailOrderByChangedAtDesc(email);
        if (!historyList.isEmpty()) historyRepo.deleteAll(historyList);

        loginRepo.delete(user);
    }
}
