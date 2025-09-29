package com.example.Traceability.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Traceability.Entity.ApprovalEntity;
import java.util.Optional;
import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
	
    Optional<ApprovalEntity> findByProductId(Long productId);
    
    List<ApprovalEntity> findByStatus(String status);
}
