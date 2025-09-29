package com.example.Traceability.DTO;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraceabilityDTO {

    private ProductDTO product;
    
    private List<MaterialDTO> rawMaterials;
    
    private List<LogisticsDTO> logistics;
    
    private List<RetailerDTO> retailers;

    private String recyclingProcedure;
    
    private LocalDateTime lastUpdated;
}
