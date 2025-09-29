package com.example.Traceability.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Traceability.Repository.ApprovalRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trace")
@RequiredArgsConstructor
public class TracePublicController {

    private final ApprovalRepository approvalRepo;

    @GetMapping("/product/{productId}/qr")
    public ResponseEntity<?> getProductQr(@PathVariable Long productId) {
    	
        return approvalRepo.findByProductId(productId)
                .map(a -> {
                    if (!"APPROVED".equalsIgnoreCase(a.getStatus())) {
                        return ResponseEntity.badRequest().body("QR not approved");
                        }
                    return ResponseEntity.ok(a.getQrBase64());
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
