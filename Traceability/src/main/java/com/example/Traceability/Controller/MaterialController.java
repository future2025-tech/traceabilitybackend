package com.example.Traceability.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Traceability.DTO.MaterialDTO;
import com.example.Traceability.ServiceImpl.MaterialServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/material")
@AllArgsConstructor
public class MaterialController {
	
	private final MaterialServiceImpl materialServiceImpl;
	
	@PostMapping
	public MaterialDTO createMaterial(@RequestBody MaterialDTO materialDTO) {
	
		return materialServiceImpl.createMaterial(materialDTO);
	}
	
	@GetMapping("/{id}")
	public MaterialDTO getMaterialById(@PathVariable Long id) {
		
		return materialServiceImpl.getMaterialById(id); 
	}
	
	@GetMapping
	public List<MaterialDTO> getAllMaterials(){
		
		return materialServiceImpl.getAllMaterials();
	}
	
	@PutMapping("/{id}")
	public MaterialDTO updateMaterial(@PathVariable Long id,
			@RequestBody MaterialDTO materialDTO) {
		
		return materialServiceImpl.updateMaterial(id, materialDTO);
	}
	
	@DeleteMapping("/{id}")
	public String deleteMaterial(@PathVariable Long id) {
		
		materialServiceImpl.deleteMaterial(id);
		
		return "Material Deleted Successfully...!";
	}
}
