package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.ProductDto;
import com.sheryians.major.entity.Category;
import com.sheryians.major.entity.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
//@RestController
//@RequestMapping("api")
public class AdminController {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	// show in admin page using in chorem
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	// show categories add page using in chorem
	@GetMapping("/admin/categories/add")
	public String adminCatAdd(Model module) {
		module.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	// add some categorys using in chorem
	@PostMapping("/admin/categories/add")
	public String adminCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	// show all categories using in chrome
	@GetMapping("/admin/categories")
	public String adminCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	// delet categories page and delete categories ussing in chrome
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.removeCategorysById(id);
		return "redirect:/admin/categories";
	}

	// update categories page and update categories using in chrome
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);

		// check category is present or not
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else
			return "404";
	}

	// add some categorys using in postman
	@PostMapping("/addCategorys")
	public String Addproduct(@RequestBody Category category) {
		categoryService.store(category);
		return "saved";
	}

	// show all categories using in post man
	@GetMapping("/getAllCategories")
	public List<Category> adminCat() {
		return categoryService.getAllCat();
	}

	// delete categories using in post man
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		categoryService.delete(id);
	}

	// update cat using in post man
	@PutMapping("/update")
	public Category update(@RequestBody Category request) {
		return categoryService.update(request);
	}

	// \\\\\\ PRODUCT SECTION/////\\\

	// get all products page using in chrome
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}

	// add product pag in chrome
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDto());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}

	// add a product using in chrome
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO") ProductDto productDto,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imageName)
			throws IOException {

		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
		product.setPrice(productDto.getPrice());
		product.setWeight(productDto.getWeight());
		product.setDescription(productDto.getDescription());
		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = imageName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);

		return "redirect:/admin/products";
	}

	// add product using in postman
	@PostMapping("/AddProduct")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	// get all product using in post man
	@GetMapping("/getAllProduct")
	public List<Product> products() {
		return productService.getAllProduct();
	}

	//delete product using in post man
	@DeleteMapping("/deletProduct/{id}")
	public void DeletById(@PathVariable("id") long id) {
		productService.removeProductById(id);
	}
	//update prodect using in post man
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody ProductDto product) {
		return productService.update(product);
	}

	// delete product page and delete product using in chrome
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}

	// upate product page and product using in chrome
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable long id, Model model) {

		Product product = productService.getProductById(id).get();

		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setPrice(product.getPrice());
		productDto.setWeight(product.getWeight());
		productDto.setDescription(product.getDescription());
		productDto.setImageName(product.getImageName());

		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDto);

		return "productsAdd";
	}

}
