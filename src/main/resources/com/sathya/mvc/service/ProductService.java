package com.sathya.mvc.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.model.ProductModel;
import com.sathya.mvc.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public void saveProductData(ProductModel productModel) {
		double price  = productModel.getPrice(); 
	    int quantity = productModel.getQuantity();
	    double totalAmount = price * quantity;
	    double tax= totalAmount * 18/100;
	    ProductEntity productEntity = new ProductEntity();
	    productEntity.setName(productModel.getName());
	    productEntity.setPrice(price);
	    productEntity.setBrand(productModel.getBrand());
	    productEntity.setQuantity(productModel.getQuantity());
	    productEntity.setMadein(productModel.getMadein());
	    productEntity.setTotalAmount(totalAmount);
	    productEntity.setCreatedAt(LocalDateTime.now());
	    productEntity.setCreatedBy(System.getProperty("user.name"));
	    productEntity.setTaxAmount(tax);
	    
	    productRepository.save(productEntity);
	    
	    
	    
		
		
	}
	
}
