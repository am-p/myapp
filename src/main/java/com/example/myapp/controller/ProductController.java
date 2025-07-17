package com.example.myapp.controller;

import com.example.myapp.model.Product;
import com.example.myapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
	return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	return repository.findById(id)
	    .map(ResponseEntity::ok)
	    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	if (repository.existsById(id)) {
	    repository.deleteById(id);
	    return ResponseEntity.noContent().build(); 
	} else {
	    return ResponseEntity.notFound().build(); 
	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
	return repository.findById(id)
	    .map(product -> {
		    product.setName(updatedProduct.getName());
		    product.setDescription(updatedProduct.getDescription());
		    product.setPrice(updatedProduct.getPrice());
		    product.setCategory(updatedProduct.getCategory());
		    product.setImageUrl(updatedProduct.getImageUrl());
		    product.setStock(updatedProduct.getStock());
		    Product saved = repository.save(product);
		    return ResponseEntity.ok(saved);
		})
	    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
					@RequestParam(required = false) String name,
					@RequestParam(required = false) String category
					) {
	if (name != null && category != null) {
	    return repository.findByNameContainingIgnoreCase(name)
		.stream()
		.filter(p -> p.getCategory().toLowerCase().contains(category.toLowerCase()))
		.toList();
	} else if (name != null) {
	    return repository.findByNameContainingIgnoreCase(name);
	} else if (category != null) {
	    return repository.findByCategoryContainingIgnoreCase(category);
	} else {
	    return repository.findAll();
	}
    }

    
}
