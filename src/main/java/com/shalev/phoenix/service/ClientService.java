package com.shalev.phoenix.service;

import com.shalev.phoenix.model.Client;
import com.shalev.phoenix.model.ContactType;
import com.shalev.phoenix.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    // In-memory "database" of clients
    private static final Map<Long, Client> clients = new HashMap<>();
    // Set of client IDs that are authenticated (simulating logged-in sessions)
    private static final Set<Long> loggedInClients = new HashSet<>();

    @Autowired
    private ProductService productService;

    // Create a new client and add it to the in-memory store
    public Client createClient(Long id, String name, String contactType, String contactValue) {
        if (clients.containsKey(id)) {
            throw new RuntimeException("Client with id " + id + " already exists");
        }

        ContactType type;
        try {
            type = ContactType.valueOf(contactType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid contact type: " + contactType);
        }

        Client client = new Client(id, name);
        client.addContactMethod(type, contactValue);  // add initial contact method
        clients.put(id, client);
        return client;
    }

    // Authenticate client by id and contact information
    public boolean authenticate(Long clientId, String contactType, String value) {
        Client client = clients.get(clientId);
        if (client == null) {
            return false;  // client does not exist
        }

        ContactType type;
        try {
            type = ContactType.valueOf(contactType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid contact type: " + contactType);
        }

        boolean matches = client.matchesContact(type, value);
        if (matches) {
            loggedInClients.add(clientId);  // mark client as "logged in"
        }
        return matches;
    }

    // Get list of products for a client (only if authenticated)
    public List<Product> getClientProducts(Long clientId) {
        if (!loggedInClients.contains(clientId)) {
            throw new SecurityException("Client is not authenticated");
        }
        Client client = clients.get(clientId);
        if (client == null) {
            throw new RuntimeException("Client with id " + clientId + " not found");
        }
        return client.getProductIds().stream()
                .map(productService::getProductById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Purchase a product for a client
    public void buyProduct(Long clientId, Long productId) {
        if (!loggedInClients.contains(clientId)) {
            throw new SecurityException("Client must be authenticated before purchasing");
        }
        Client client = clients.get(clientId);
        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product with id " + productId + " does not exist");
        }
        if (client.getProductIds().contains(productId)) {
            throw new RuntimeException("Client has already purchased this product");
        }
        client.getProductIds().add(productId);
    }

    // Get All Clients
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }
}
