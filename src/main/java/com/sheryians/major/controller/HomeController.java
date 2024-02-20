package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
//@RestController
//@RequestMapping("/api/Home")
public class HomeController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	// it will using to home button
	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	}

	// it will using shop button
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop";
	}

	//Using to filter by category
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable  long id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		return "shop";
	}
	
	// Using to viewproduct button
	@GetMapping ("/shop/viewproduct/{id}")
	public String viewProduct (Model model, @PathVariable long id) {
	model.addAttribute("product", productService.getProductById(id).get());
	model.addAttribute("cartCount",GlobalData.cart.size());
	return "viewProduct";
	}
}
