package com.sheryians.major.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Long id;

	private String name;

	private int categoryId;

	private double price;

	private double weight;

	private String description;

	private String imageName;
}
