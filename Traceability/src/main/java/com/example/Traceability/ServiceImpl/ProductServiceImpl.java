package com.example.Traceability.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Traceability.DTO.ProductDTO;
import com.example.Traceability.Entity.ProductEntity;
import com.example.Traceability.Repository.ProductRepository;
import com.example.Traceability.Service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ModelMapper modelMapper;
	private final ProductRepository productRepository;

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		
		ProductEntity entity = modelMapper.map(productDTO, ProductEntity.class);
		
		entity.setTimeStamp(LocalDateTime.now().withNano(0));
		
		ProductEntity savedEntity = productRepository.save(entity);
			
		return modelMapper.map(savedEntity, ProductDTO.class);
	}

	@Override
	public List<ProductDTO> getAllProducts() {

		return productRepository.findAll().stream()
				.map(entity ->modelMapper.map(entity, ProductDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(Long id) {
		
		ProductEntity entity = productRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Product not found by the given id"));
		
		return modelMapper.map(entity, ProductDTO.class);
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		
		ProductEntity entity = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product Id not  Found"));
		
		entity.setProductName(productDTO.getProductName());
		entity.setProductCategory(productDTO.getProductCategory());
		entity.setProductEventType(productDTO.getProductEventType());
		entity.setCo2Emission(productDTO.getCo2Emission());
		entity.setEnergyConsumption(productDTO.getEnergyConsumption());
		entity.setWaterUsage(productDTO.getWaterUsage());
		entity.setWasteGenerated(productDTO.getWasteGenerated());
		entity.setProductDescription(productDTO.getProductDescription());
		entity.setTimeStamp(productDTO.getTimeStamp());
		entity.setProductLocation(productDTO.getProductLocation());
		
		return modelMapper.map(entity, ProductDTO.class);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
		
	}

}
