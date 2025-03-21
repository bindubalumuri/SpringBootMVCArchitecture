package com.sathya.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.model.ProductModel;
import com.sathya.mvc.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

   @Autowired
   ProductService productService;

//   @GetMapping("/wish")
//   public String greet() {
//       return "hello";
//   }
   
	
	  @GetMapping("/productform") 
	  public String getForm(Model model) {
	  
	  ProductModel productModel = new ProductModel(); 
	  productModel.setBrand("Apple");
	  productModel.setMadein("India");
	  model.addAttribute("productModel", productModel);
	  return  "add-product" ; 
	  }
	 
   
   @PostMapping("/saveproduct")
   public String saveProductData(@Valid @ModelAttribute ProductModel product,BindingResult bindingResult)
   {
	   if(bindingResult.hasErrors())
	   {
		   return "add-product";
	   }
	   productService.saveProductData(product);
	   return "Success";
   }
   
   
   
   @GetMapping("/getAllProducts")
   public String getAllProdcuts(Model model)
   
   {
	   List<ProductEntity> products = productService.getAllProducts();
	   model.addAttribute("products", products);
	   return "products-list";
   }
   @GetMapping("/getProduct/{id}")
   public String getProductById(@PathVariable Long id, Model model)
   {
	   ProductEntity product = productService.getProductById(id);
	   model.addAttribute("product",product);
	   return "productById";
	   
   }
   
   @GetMapping("/searchByEmail")
   public String searchProductByEmail(@RequestParam String email, Model model) {
       ProductEntity product = productService.getProductByEmail(email);
       model.addAttribute("product",product);
     
       return "productById";
   }

   
   @GetMapping("/deleteProduct/{id}")
   public String deleteProductById(@PathVariable("id") Long Id)
   {
	   productService.deleteProductById(Id);
	   return "redirect:/getAllProducts";
	   
   }
   
   
   @GetMapping("/editProduct/{id}")
   public String editProductForm(@PathVariable Long id, Model model) {
       ProductModel productModel = productService.getEditProduct(id);
       model.addAttribute("productModel", productModel); // Changed to 'productModel'
       model.addAttribute("id", id);
       return "edit-Product";
   }

   
     @GetMapping("/about")
   public String aboutPage() {
       return "AboutUs";  // Renders about.html
   }

   // GET - Show Contact Form
   @GetMapping("/contact")
   public String contactForm() {
       return "ContactUs";  // Renders contact.html
   }
   
   @PostMapping("/updateProduct/{id}")
   public String updateProduct(@PathVariable Long id, @ModelAttribute ProductModel productModel) {
       productService.updateProduct(id, productModel);
       return "redirect:/getAllProducts";
   }

}
