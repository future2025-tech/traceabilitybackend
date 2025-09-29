package com.example.Traceability.Controller;

import java.util.Base64;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Traceability.Entity.ApprovalEntity;
import com.example.Traceability.Repository.ApprovalRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trace")
@RequiredArgsConstructor
public class TracePublicController {

    private final ApprovalRepository approvalRepo;

    // Returns QR as PNG image directly
    @GetMapping("/product/{productId}/qr")
    public ResponseEntity<byte[]> getProductQr(@PathVariable Long productId) {

        List<ApprovalEntity> approvals = approvalRepo.findAllByProductId(productId);

        // Only return the first approved QR
        return approvals.stream()
                .filter(a -> "APPROVED".equalsIgnoreCase(a.getStatus()))
                .findFirst()
                .map(a -> {
                    byte[] qrBytes = Base64.getDecoder().decode(a.getQrBase64());
                    return ResponseEntity.ok()
                            .header("Content-Type", "image/png")
                            .body(qrBytes);
                })
                .orElse(ResponseEntity.badRequest().body(null));
    }
}
