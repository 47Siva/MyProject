package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.dto.ProductDto;
import com.sheryians.major.entity.Product;
import com.sheryians.major.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	// show to all products in chorem and post man
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	// add product in chrome and post man
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	// get product from catid in chrome and post man
	public List<Product> getAllProductsByCategoryId(long id) {
		return productRepository.findAllCategoryById(id);
	}

	// delete product in chrome and post man
	public void removeProductById(long id) {
		productRepository.deleteById(id);
	}

	// find by product id chrome and post man
	public Optional<Product> getProductById(long id) {
		return productRepository.findById(id);
	}

	//update product in post man
	public Product update(ProductDto productDto) {
		
		Long id = productDto.getId();
		
		Product product = productRepository.findById(id).get();

		ProductDto productDto1 = new ProductDto();
		productDto1.setId(product.getId());
		productDto1.setName(product.getName());
		productDto1.setCategoryId(product.getCategory().getId());
		productDto1.setPrice(product.getPrice());
		productDto1.setWeight(product.getWeight());
		productDto1.setDescription(product.getDescription());
		productDto1.setImageName(product.getImageName());
	
		
		return productRepository.save(productDto1);
	}

}
