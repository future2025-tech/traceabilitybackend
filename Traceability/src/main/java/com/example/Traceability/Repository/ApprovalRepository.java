package com.example.Traceability.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Traceability.Entity.ApprovalEntity;
import java.util.Optional;
import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
	
	// If you want multiple approvals for the same product
    List<ApprovalEntity> findAllByProductId(Long productId);

    // If only one approval per product is allowed
    Optional<ApprovalEntity> findFirstByProductIdOrderByReviewedAtDesc(Long productId);

    // Optionally, find pending approvals
    List<ApprovalEntity> findByStatus(String status);
}
