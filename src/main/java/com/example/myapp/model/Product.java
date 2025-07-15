package com.example.myapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 0, message = "Price must be >= 0")
    private double price;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Image URL is required")
    @Column(name = "image_url")
    private String imageUrl;

    @Min(value = 0, message = "Stock must be >= 0")
    private int stock;
    

    public Product() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getImageUrl() { return imageUrl; }
    public int getStock() { return stock; }

    public void setName(String name) {
	this.name = name;
    }
    public void setDescription(String description) {
	this.description = description;
    }
    public void setPrice(double price) {
	this.price = price;
    }
    public void setCategory(String category) {
	this.category = category;
    }
    public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
    }
    public void setStock(int stock) {
	this.stock = stock;
    }


}
