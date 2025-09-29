package com.example.Traceability.DTO;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsDTO {

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
	
    private String selectProduct;

	private LocalDateTime timeStamp;
}
