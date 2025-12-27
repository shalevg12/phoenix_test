package com.shalev.phoenix.dto;

import com.shalev.phoenix.model.ContactType;

public class AuthRequest {
    private Long clientId;
    private ContactType contactType;
    private String contactValue;

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public ContactType getContactType() { return contactType; }
    public void setContactType(ContactType contactType) { this.contactType = contactType; }

    public String getContactValue() { return contactValue; }
    public void setContactValue(String contactValue) { this.contactValue = contactValue; }
}
