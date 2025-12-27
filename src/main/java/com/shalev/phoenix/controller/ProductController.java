package com.shalev.phoenix.controller;

import com.shalev.phoenix.model.Product;
import com.shalev.phoenix.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add a new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
        try {
            Product created = productService.addProduct(
                    newProduct.getId(),
                    newProduct.getName(),
                    newProduct.getDescription()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update an existing product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody Product updateDetails) {
        try {
            Product updated = productService.updateProduct(
                    productId,
                    updateDetails.getName(),
                    updateDetails.getDescription()
            );
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
