package com.sathya.mvc.entity;



import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	private double price;
	private int quantity;
	
	private String brand;
	private String madein;
	
	private LocalDateTime createdAt;
	private String createdBy;
	
	private double totalAmount;
	private double taxAmount;
	
	private String email;

	
}
 