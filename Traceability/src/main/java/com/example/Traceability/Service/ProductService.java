package com.example.Traceability.Service;

import java.util.List;

import com.example.Traceability.DTO.ProductDTO;

public interface ProductService {
	
	public ProductDTO createProduct(ProductDTO productDTO);
	
	public List<ProductDTO> getAllProducts();
	
	public ProductDTO getProductById(Long id);
	
	public ProductDTO updateProduct(Long id, ProductDTO productDT);
	
	public void deleteProduct(Long id);

}
