package com.shalev.phoenix.dto;

import com.shalev.phoenix.model.ContactType;

public class CreateClientRequest {
    private Long id;
    private String name;
    private ContactType contactType;
    private String contactValue;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ContactType getContactType() { return contactType; }
    public void setContactType(ContactType contactType) { this.contactType = contactType; }

    public String getContactValue() { return contactValue; }
    public void setContactValue(String contactValue) { this.contactValue = contactValue; }
}
