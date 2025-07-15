package com.example.myapp.controller;

import com.example.myapp.model.CustomeOrder;
import com.example.myapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.myapp.repository.ProductRepository;
import com.example.myapp.model.Product;
import com.example.myapp.model.OrderLine;
import com.example.myapp.exception.StockInsufficientException;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public CustomeOrder createOrder(@RequestBody CustomeOrder order) {
	for (OrderLine line : order.getLines()) {
	    Product product = productRepository.findById(line.getProduct().getId())
		.orElseThrow(() -> new RuntimeException("Product not found"));
	    if (product.getStock() < line.getQuantity()) {
		throw new StockInsufficientException("Not enough stock for product: " + product.getName());
	    }
	    product.setStock(product.getStock() - line.getQuantity());
	    productRepository.save(product);
	    line.setProduct(product);
	}
	return orderRepository.save(order);
    }


    @GetMapping
    public List<CustomeOrder> getAllOrders() {
        return orderRepository.findAll();
    }

}
