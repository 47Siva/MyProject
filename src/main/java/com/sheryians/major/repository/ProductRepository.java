package com.sheryians.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sheryians.major.dto.ProductDto;
import com.sheryians.major.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select * from product p where p.category_id=:id",nativeQuery = true)
	List<Product> findAllCategoryById(long id);

	Product save(ProductDto productDto);

}
