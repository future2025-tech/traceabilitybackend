package com.example.Traceability.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Traceability.Entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String>{

	LoginEntity findByEmail(String email);
}
