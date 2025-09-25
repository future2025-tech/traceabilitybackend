package com.example.Traceability.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Traceability.Entity.LoginEntity;
import com.example.Traceability.Entity.PasswordHistory;
import com.example.Traceability.Repository.LoginRepository;
import com.example.Traceability.Repository.PasswordHistoryRepository;
import com.example.Traceability.Service.loginService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginSerivceImpl implements loginService{
	
	private final LoginRepository loginRepo;
    private final PasswordHistoryRepository historyRepo;
    private final BCryptPasswordEncoder encoder;
	    
    @Transactional
    public LoginEntity createUser(String email, String rawPassword) {
    	
        if (loginRepo.findByEmail(email) != null) {
            throw new IllegalStateException("User with this email already exists");
        }

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

        return user;
    }

	    
    public boolean isPasswordUsedBefore(String email, String newPassword) {
	    	
        List<PasswordHistory> history = historyRepo
        		.findTop3ByEmailOrderByChangedAtDesc(email);
	        
        for (PasswordHistory ph : history) {
            if (encoder.matches(newPassword, ph.getPassword()))
            	return true;
        }
        return false;
    }

    public void updatePassword(String email, String newPassword) {
	       
    	LoginEntity user = loginRepo.findByEmail(email);
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
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<PasswordHistory> historyList = historyRepo
        		.findTop3ByEmailOrderByChangedAtDesc(email);
	        
        if (!historyList.isEmpty()) {
            historyRepo.deleteAll(historyList);
        }

        loginRepo.delete(user);
    }
    

    public void savePasswordHistory(String email, String newPasswordHash) {
        PasswordHistory history = new PasswordHistory();
        history.setEmail(email);
        history.setPassword(newPasswordHash);
        history.setChangedAt(LocalDateTime.now());
        historyRepo.save(history);
    }
}
