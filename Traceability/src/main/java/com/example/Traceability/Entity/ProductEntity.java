package com.example.Traceability.Entity;

import java.time.LocalDateTime;

import com.example.Traceability.Constant.Category;
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
@Table(name = "TC_PRODUCT")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String productName;
	
	private Category productCategory;
	   	
	private String ProductEvent;
	
	private Float co2Emission;
	
	private Float waterUsage;
	
	private Float energyConsumption;
	
	private Float wasteGenerated;
	
	private String productEventType;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private LocalDateTime productTimeStamp;
	
	private String productDescription;
	
}
