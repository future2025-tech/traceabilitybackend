package com.example.Traceability.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TC_RAWMATERIALS")
public class MaterialEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private LocalDateTime MaterialEntryTime;
}
