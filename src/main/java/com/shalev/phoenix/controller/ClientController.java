package com.shalev.phoenix.controller;

import com.shalev.phoenix.dto.AuthRequest;
import com.shalev.phoenix.dto.CreateClientRequest;
import com.shalev.phoenix.model.Client;
import com.shalev.phoenix.model.Product;
import com.shalev.phoenix.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // 1. Create a new client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody CreateClientRequest request) {
        try {
            Client newClient = clientService.createClient(
                    request.getId(),
                    request.getName(),
                    request.getContactType(),
                    request.getContactValue()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 2. Authenticate an existing client (login)
    @PostMapping("/auth")
    public ResponseEntity<String> authenticateClient(@RequestBody AuthRequest auth) {
        boolean success = clientService.authenticate(
                auth.getClientId(),
                auth.getContactType(),
                auth.getContactValue()
        );
        if (success) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed: please check your credentials");
        }
    }

    // 3. Get client products (only if authenticated)
    @GetMapping("/{clientId}/products")
    public ResponseEntity<List<Product>> getClientProducts(@PathVariable Long clientId) {
        try {
            List<Product> products = clientService.getClientProducts(clientId);
            return ResponseEntity.ok(products);
        } catch (SecurityException se) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 4. Purchase a product for a client
    @PostMapping("/{clientId}/products/{productId}")
    public ResponseEntity<String> buyProduct(@PathVariable Long clientId,
                                             @PathVariable Long productId) {
        try {
            clientService.buyProduct(clientId, productId);
            return ResponseEntity.ok("Product " + productId + " purchased by client " + clientId);
        } catch (SecurityException se) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Client not authenticated. Please login first.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
