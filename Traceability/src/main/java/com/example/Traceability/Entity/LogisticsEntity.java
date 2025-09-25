package com.example.Traceability.Entity;

import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "TC_LOGISTICS")
public class LogisticsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logisticsId;
	
	private String LogisticName;
	
	private String logisticType;
	
	private String trasportMode;
	
	private String logisticPartner;
	
	private String sourceLocation;
	
	private String destinationlocation;
	
	private Date expectedDeliveryDate;
	
	private String logisticStatus;
	
	private String eventType;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private LocalDateTime timeStamp;
}
