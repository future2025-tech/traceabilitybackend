package com.example.Traceability.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDTO {

	private Long materialId;
	
	private String materialName;
	
	private String materialType;
	
	private Float compositionValue;
	
	private String materialOrigin;
	
	private String unit;
	
	private String substancesOfConcern;
	
	private Float co2Emission;
	
	private Float waterUsage;
	
	private Float energyConsumption;
	
	private Float wasteGenerated;
	
	private String eventType;
	
	private LocalDateTime MaterialEntryTime;
	
	private String materialLocation;
	
	private String selectProduct;

}
