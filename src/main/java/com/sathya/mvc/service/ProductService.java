package com.sathya.mvc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	    productEntity.setEmail(productModel.getEmail());
	    productEntity.setCreatedBy(System.getProperty("user.name"));
	    productEntity.setTaxAmount(tax);
	    
	    productRepository.save(productEntity);
	    
	 }

	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}

	public ProductEntity getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
				
		
		
	}

	public void  deleteProductById(Long Id) {
		productRepository.deleteById(Id);
	}

	

	public void updateProduct(Long id, ProductModel productModel) {
	    ProductEntity productEntity = productRepository.findById(id).orElse(null);
               if (productEntity != null) {
	   
	        productEntity.setName(productModel.getName());
	        productEntity.setBrand(productModel.getBrand());
	        productEntity.setPrice(productModel.getPrice());
	        productEntity.setQuantity(productModel.getQuantity());
	        productEntity.setMadein(productModel.getMadein());

	        // Recalculate amounts
	        double totalAmount = productEntity.getPrice() * productEntity.getQuantity();
	        double tax = totalAmount * 18 / 100;

	        productEntity.setTotalAmount(totalAmount);
	        productEntity.setTaxAmount(tax);
	        productEntity.setEmail(productModel.getEmail());
	        productEntity.setCreatedAt(LocalDateTime.now());
	        productEntity.setCreatedBy(System.getProperty("user.name"));

	        // Save to DB
	        productRepository.save(productEntity);
	    }
	    else {
	        System.out.println("Product not found with ID: " + id);
	        }
	    }
	

	public ProductEntity getProductByEmail(String email) {
	    return productRepository.findByEmail(email);
	}

	public ProductModel getEditProduct(Long id) {
		ProductEntity productEntity = productRepository.findById(id).orElse(null);
		ProductModel productModel = new ProductModel();
		
		productModel.setName(productEntity.getName());
		productModel.setPrice(productEntity.getPrice());
		productModel.setQuantity(productEntity.getQuantity());
		productModel.setBrand(productEntity.getBrand());
		productModel.setMadein(productEntity.getMadein());
		productModel.setEmail(productEntity.getEmail());
		return productModel;
	}

}
