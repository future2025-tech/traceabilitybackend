package com.example.Traceability.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Traceability.DTO.LogisticsDTO;
import com.example.Traceability.DTO.MaterialDTO;
import com.example.Traceability.DTO.ProductDTO;
import com.example.Traceability.DTO.RetailerDTO;
import com.example.Traceability.DTO.TraceabilityDTO;
import com.example.Traceability.Entity.ApprovalEntity;
import com.example.Traceability.Entity.LogisticsEntity;
import com.example.Traceability.Entity.MaterialEntity;
import com.example.Traceability.Entity.ProductEntity;
import com.example.Traceability.Entity.RetailerEntity;
import com.example.Traceability.Repository.ApprovalRepository;
import com.example.Traceability.Repository.LogisticsRepository;
import com.example.Traceability.Repository.MaterialRepository;
import com.example.Traceability.Repository.ProductRepository;
import com.example.Traceability.Repository.RetailerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl {

    private final ApprovalRepository approvalRepo;
    private final QRCodeServiceImpl qrCodeService;
    private final ProductRepository productRepo;
    private final MaterialRepository materialRepo;
    private final LogisticsRepository logisticsRepo;
    private final RetailerRepository retailerRepo;
    private final ModelMapper modelMapper;

    /**
     * Creates a new approval request for a product.
     */
    public ApprovalEntity requestApproval(Long productId, String requestedBy) {
        productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        ApprovalEntity approval = new ApprovalEntity();
        approval.setProductId(productId);
        approval.setRequestedBy(requestedBy);
        approval.setRequestedAt(LocalDateTime.now());
        approval.setStatus("PENDING");

        return approvalRepo.save(approval);
    }

    /**
     * Reviews an approval request.
     */
    public ApprovalEntity reviewApproval(Long approvalId, boolean approve,
                                         String adminUser, String remarks) throws Exception {

        ApprovalEntity approval = approvalRepo.findById(approvalId)
                .orElseThrow(() -> new IllegalArgumentException("Approval not found: " + approvalId));

        approval.setReviewedBy(adminUser);
        approval.setReviewedAt(LocalDateTime.now());
        approval.setRemarks(remarks);

        if (!approve) {
            approval.setStatus("REJECTED");
            return approvalRepo.save(approval);
        }

        Long productId = approval.getProductId();

        if (!isTraceComplete(productId)) {
            throw new IllegalStateException("Traceability incomplete for product: " + productId);
        }

        TraceabilityDTO dto = buildTraceabilityDTO(productId);

        // Generate QR code and mark approval as approved
        String qrBase64 = qrCodeService.generateQRCodeBase64(dto);
        approval.setQrBase64(qrBase64);
        approval.setStatus("APPROVED");

        return approvalRepo.save(approval);
    }

    /**
     * Checks if a product's traceability is complete.
     */
    public boolean isTraceComplete(Long productId) {
        return productRepo.existsById(productId)
                && !safeFindMaterialsByProductId(productId).isEmpty()
                && !safeFindLogisticsByProductId(productId).isEmpty()
                && !safeFindAllRetailersByProductId(productId).isEmpty();
    }

    /**
     * Builds a TraceabilityDTO from productId.
     */
    private TraceabilityDTO buildTraceabilityDTO(Long productId) {
        ProductEntity product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        List<MaterialEntity> materials = safeFindMaterialsByProductId(productId);
        List<LogisticsEntity> logistics = safeFindLogisticsByProductId(productId);
        List<RetailerEntity> retailers = safeFindAllRetailersByProductId(productId);

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<MaterialDTO> materialDTOs = materials.stream()
                .map(m -> modelMapper.map(m, MaterialDTO.class))
                .collect(Collectors.toList());
        List<LogisticsDTO> logisticsDTOs = logistics.stream()
                .map(l -> modelMapper.map(l, LogisticsDTO.class))
                .collect(Collectors.toList());
        List<RetailerDTO> retailerDTOs = retailers.stream()
                .map(r -> modelMapper.map(r, RetailerDTO.class))
                .collect(Collectors.toList());

        TraceabilityDTO dto = new TraceabilityDTO();
        dto.setProduct(productDTO);
        dto.setRawMaterials(materialDTOs);
        dto.setLogistics(logisticsDTOs);
        dto.setRetailers(retailerDTOs);
        dto.setRecyclingProcedure("Return to authorized recycling center");
        dto.setLastUpdated(LocalDateTime.now());

        return dto;
    }

    /**
     * Utility methods to fetch entities safely.
     */
    private List<MaterialEntity> safeFindMaterialsByProductId(Long productId) {
        return materialRepo.findAll().stream()
                .filter(m -> productId.toString().equals(m.getSelectProduct()))
                .toList();
    }

    private List<LogisticsEntity> safeFindLogisticsByProductId(Long productId) {
        return logisticsRepo.findAll().stream()
                .filter(l -> productId.toString().equals(l.getSelectProduct()))
                .toList();
    }

    private List<RetailerEntity> safeFindAllRetailersByProductId(Long productId) {
        return retailerRepo.findAll().stream()
                .filter(r -> productId.toString().equals(r.getSelectProduct()))
                .toList();
    }

    /**
     * Queries
     */
    public List<ApprovalEntity> findPendingApprovals() {
        return approvalRepo.findByStatus("PENDING");
    }

    public Optional<ApprovalEntity> findById(Long id) {
        return approvalRepo.findById(id);
    }
}
