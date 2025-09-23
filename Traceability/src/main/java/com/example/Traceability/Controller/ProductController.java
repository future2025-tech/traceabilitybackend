package com.example.Traceability.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Traceability.DTO.ProductDTO;
import com.example.Traceability.ServiceImpl.ProductServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
	
	private final ProductServiceImpl productServiceImpl;
	
	@PostMapping
	public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
		
		return productServiceImpl.createProduct(productDTO);
	}
	
	@GetMapping("/{id}")
	public ProductDTO getproductById(@PathVariable Long id) {
		
		return productServiceImpl.getProductById(id);
	}
	
	@GetMapping
	public List<ProductDTO> getAllProducts(){
		
		return productServiceImpl.getAllProducts();
	}
	
	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Long id,
			@RequestBody ProductDTO productDTO) {
		
		return productServiceImpl.updateProduct(id, productDTO);
	}
	
	@DeleteMapping("/{id}")
	public String deletePRocudt(@PathVariable Long id) {
		
		productServiceImpl.deleteProduct(id);
		
		return "Account Deleted Successfully: ";
	}

}
