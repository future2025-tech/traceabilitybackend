package com.example.Traceability.Controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Traceability.DTO.RetailerDTO;
import com.example.Traceability.ServiceImpl.RetailerServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/retailer")
@AllArgsConstructor
public class RetailerController {

	private final RetailerServiceImpl retailerService;
	
	@PostMapping
    public RetailerDTO createRetailer(@RequestBody RetailerDTO dto){

        return retailerService.createRetailer(dto);
    }
	
	@GetMapping("/{id}")
	public RetailerDTO getRetailerById(@PathVariable Long id) {
		
		return retailerService.getRetailerById(id);
	}
	
	@GetMapping
	public List<RetailerDTO> getAllRetailers() {
		
		return retailerService.getAllRetailers();
	}
	
	@PutMapping("/{id}")
	public RetailerDTO updateRetailer(@PathVariable Long id
			, @RequestBody RetailerDTO retailerDTO) {
		
		return retailerService.updateRetailer(id, retailerDTO);
	}
	
	@DeleteMapping("/{id}")
	public String deleteRetailer(@PathVariable Long id) {
		
		retailerService.deleteRetailerById(id);
		
		return " Retailer Deleted Sucessfully...!";
	}
	
	 @GetMapping("/{id}/invoice")
	    public ResponseEntity<byte[]> getInvoice(@PathVariable Long id) {
	        RetailerDTO retailer = retailerService.getRetailerById(id);

	        if (retailer.getUploadInvoice() == null) {
	            return ResponseEntity.notFound().build();
	        }

	        byte[] invoiceBytes = java.util.Base64.getDecoder()
	        		.decode(retailer.getUploadInvoice());
	        
	        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	        		"inline; filename=invoice_" + id + ".png")
	                .contentType(MediaType.IMAGE_PNG)
	                .body(invoiceBytes);
	    }
}
