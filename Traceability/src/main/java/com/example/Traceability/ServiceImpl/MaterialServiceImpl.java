package com.example.Traceability.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Traceability.DTO.MaterialDTO;
import com.example.Traceability.Entity.MaterialEntity;
import com.example.Traceability.Repository.MaterialRepository;
import com.example.Traceability.Service.MaterialService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {
	
	private final MaterialRepository materialRepo;
	private final ModelMapper modelMapper;

	@Override
	public MaterialDTO createMaterial(MaterialDTO materialDTO) {
		
		MaterialEntity entity = modelMapper.map(materialDTO, MaterialEntity.class);
		
		entity.setMaterialEntryTime(LocalDateTime.now().withNano(0));
		
		MaterialEntity savedEntity = materialRepo.save(entity);
		
		return modelMapper.map(savedEntity, MaterialDTO.class);
	}

	@Override
	public MaterialDTO getMaterialById(Long id) {
				
		MaterialEntity savedEntity = materialRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not Found"));
		
		return modelMapper.map(savedEntity, MaterialDTO.class);
	}

	@Override
	public List<MaterialDTO> getAllMaterials() {
		
		return materialRepo.findAll().stream().map(entity -> modelMapper
				.map(entity, MaterialDTO.class)).collect(Collectors.toList());
	}

	@Override
	public MaterialDTO updateMaterial(Long id, MaterialDTO materialDTO) {
		
		MaterialEntity entity = materialRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not found"));
		
		entity.setMaterialName(materialDTO.getMaterialName());
		entity.setMaterialType(materialDTO.getMaterialType());
		entity.setCompositionValue(materialDTO.getCompositionValue());
		entity.setMaterialType(materialDTO.getMaterialType());
		entity.setMaterialOrigin(materialDTO.getMaterialOrigin());
		entity.setSubstancesOfConcern(materialDTO.getSubstancesOfConcern());
		entity.setCo2Emission(materialDTO.getCo2Emission());
		entity.setWaterUsage(materialDTO.getWaterUsage());
		entity.setEnergyConsumption(materialDTO.getEnergyConsumption());
		entity.setWasteGenerated(materialDTO.getWasteGenerated());
		entity.setEventType(materialDTO.getEventType());
		entity.setUnit(materialDTO.getUnit());
		
		return modelMapper.map(entity, MaterialDTO.class);
	}

	@Override
	public String deleteMaterial(Long id) {
		
		materialRepo.deleteById(id);
		
		return "Material Deleted Sucessfully";
	}

}
