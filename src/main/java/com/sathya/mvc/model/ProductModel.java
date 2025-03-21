package com.sathya.mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductModel {

		
		@NotBlank(message = "Product name is required")
	    @Size(min = 5, max = 50, message = "Product name must be between 5 and 50 characters")
	    private String name;

	    @Positive(message = "Price must be greater than zero")
	    private double price;

	    @Min(value = 10, message = "Quantity must be at least 1")
	    @Max(value = 1000, message = "Quantity must not exceed 1000")
	    private int quantity;

	    @NotBlank(message = "Brand is required")
	    private String brand;

	    @NotBlank(message = "Made in is required")
	    private String madein;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Email should be valid")
	    private String email;


}
