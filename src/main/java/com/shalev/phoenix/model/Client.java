package com.shalev.phoenix.model;

import java.util.*;

public class Client {
    private Long id;
    private String name;
    private List<ContactMethod> contactMethods = new ArrayList<>();
    private Set<Long> productIds = new HashSet<>();  // IDs of products purchased by the client

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<ContactMethod> getContactMethods() { return contactMethods; }
    public Set<Long> getProductIds() { return productIds; }

    // Add a new contact method to the client
    public void addContactMethod(ContactType type, String value) {
        contactMethods.add(new ContactMethod(type, value));
    }

    // Check if a given contact method matches the client's details (for authentication)
    public boolean matchesContact(ContactType type, String value) {
        return contactMethods.stream().anyMatch(cm ->
                cm.getType() == type && cm.getValue().equals(value));
    }
}
