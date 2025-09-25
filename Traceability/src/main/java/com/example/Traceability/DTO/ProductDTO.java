package com.example.Traceability.DTO;

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
	   	
	private String ProductEvent;
	
	private Float co2Emission;
	
	private Float waterUsage;
	
	private Float energyConsumption;
	
	private Float wasteGenerated;
	
	private String productEventType;
	
	private LocalDateTime productTimeStamp;
	
	private String productLocation;
	
	private String productDescription;
	
}
