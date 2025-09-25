package com.example.Traceability.Service;

import java.util.List;

import com.example.Traceability.DTO.RetailerDTO;

public interface RetailerService {

	public RetailerDTO createRetailer(RetailerDTO retailerDTO);
	
	public RetailerDTO getRetailerById(Long id);
	
	public List<RetailerDTO> getAllRetailers();
	
	public RetailerDTO updateRetailer(Long id, RetailerDTO retailerDTO);
	
	public String deleteRetailerById(Long id);
}
