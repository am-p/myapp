package com.example.myapp.repository;

import com.example.myapp.model.CustomeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomeOrder, Long> { }
