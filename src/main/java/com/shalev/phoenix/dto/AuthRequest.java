package com.shalev.phoenix.dto;

public class AuthRequest {

    private Long clientId;
    private String contactType;
    private String contactValue;

    public Long getClientId() { return clientId; }

    public void setClientId(Long clientId) { this.clientId = clientId; }

    public String getContactType() { return contactType; }

    public void setContactType(String contactType) { this.contactType = contactType;}

    public String getContactValue() { return contactValue; }

    public void setContactValue(String contactValue) {this.contactValue = contactValue; }
}
