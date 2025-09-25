package com.example.Traceability.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailerDTO {

private Long retailerId;
	
	private String retailerName;
	
	private String retailerType;
	
	private String contactPersonName;
	
	private Long contactPersonNumber;
	
	private String retailerAddress;
	
	private String selectProduct;
	
	private Long quantitySupplied;
	
	private String eventType;
	
	private LocalDateTime timeStamp;
			
	private byte[] uploadInvoice;
	
}
