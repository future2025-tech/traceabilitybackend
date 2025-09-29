package com.example.Traceability.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TC_RETAILER")
public class RetailerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long retailerId;
	
	private String retailerName;
	
	private String retailerType;
	
	private String contactPersonName;
	
	private Long contactPersonNumber;
	
	private String retailerAddress;
	
	private String selectProduct;
	
	private Long quantitySupplied;
	
	private String eventType;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "time_stamp", nullable = false)
	private LocalDateTime timeStamp;
	
	@Column(nullable = false)
	private String retailerLocation;
			
	 @Lob
	 @Column(name = "upload_invoice", columnDefinition = "LONGBLOB")
	 private byte[] uploadInvoice;
	 
	 public String getSelectProduct() {
		    return selectProduct;
		}


}
