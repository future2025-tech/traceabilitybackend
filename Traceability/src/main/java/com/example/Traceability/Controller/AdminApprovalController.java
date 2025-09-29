package com.example.Traceability.Controller;

import com.example.Traceability.Entity.ApprovalEntity;
import com.example.Traceability.ServiceImpl.ApprovalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/approval")
@RequiredArgsConstructor
public class AdminApprovalController {

    private final ApprovalServiceImpl approvalService;

    @PostMapping("/request")
    public ResponseEntity<?> requestApproval(@RequestParam Long productId, 
    		@RequestParam String requestedBy) {
    	
        try {
            ApprovalEntity saved = approvalService.requestApproval(
            		productId, requestedBy);
            
            return ResponseEntity.ok(saved);
            
        } catch (Exception e) {
        	
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<List<ApprovalEntity>> pending() {
    	
        return ResponseEntity.ok(approvalService.findPendingApprovals());
    }

    @PostMapping("/{id}/review")
    
    public ResponseEntity<?> review(@PathVariable Long id,
                                    @RequestParam boolean approve,
                                    @RequestParam String adminUser,
                                    @RequestParam(required = false) String remarks) {
        try {
            ApprovalEntity result = approvalService.reviewApproval(
            		id, approve, adminUser, remarks);
            return ResponseEntity.ok(result);
        } catch (IllegalStateException ise) {
            return ResponseEntity.badRequest().body(ise.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
    	
        return approvalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
