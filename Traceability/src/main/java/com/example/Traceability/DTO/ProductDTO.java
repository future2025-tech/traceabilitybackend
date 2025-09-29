package com.example.Traceability.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import com.example.Traceability.Constant.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String productName;

    private Category productCategory;

    private String productEvent;

    private Float co2Emission;

    private Float waterUsage;

    private Float energyConsumption;

    private Float wasteGenerated;

    private String productEventType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;

    private String productLocation;

    private String productDescription;
}
