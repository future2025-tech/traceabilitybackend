package com.example.Traceability.Service;

import java.util.List;

import com.example.Traceability.DTO.MaterialDTO;

public interface MaterialService {

	public MaterialDTO createMaterial(MaterialDTO materialDTO);
	
	public MaterialDTO getMaterialById(Long id);
	
	public List<MaterialDTO> getAllMaterials();	
	
	public MaterialDTO updateMaterial(Long id, MaterialDTO materialDTO);
	
	public String deleteMaterial(Long id);
}
