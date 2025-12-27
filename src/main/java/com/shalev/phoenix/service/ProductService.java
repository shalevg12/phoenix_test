package com.shalev.phoenix.service;

import com.shalev.phoenix.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    // In memory "database" of products
    private static final Map<Long, Product> products = new HashMap<>();

    // Add a new product to the system
    public Product addProduct(Long id, String name, String description) {
        if (products.containsKey(id)) {
            throw new RuntimeException("Product with id " + id + " already exists");
        }
        Product product = new Product(id, name, description);
        products.put(id, product);
        return product;
    }

    // Get product by id
    public Product getProductById(Long productId) {
        return products.get(productId);
    }

    // Update an existing product
    public Product updateProduct(Long productId, String newName, String newDescription) {
        Product product = products.get(productId);
        if (product == null) {
            throw new RuntimeException("Product with id " + productId + " not found");
        }
        if (newName != null) {
            product.setName(newName);
        }
        if (newDescription != null) {
            product.setDescription(newDescription);
        }
        return product;
    }


    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

}
