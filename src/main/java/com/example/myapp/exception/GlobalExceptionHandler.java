package com.example.myapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StockInsufficientException.class)
    public ResponseEntity<String> handleStockException(StockInsufficientException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
