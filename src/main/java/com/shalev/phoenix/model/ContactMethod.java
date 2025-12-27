package com.shalev.phoenix.model;

public class ContactMethod {
    private ContactType type;
    private String value;

    public ContactMethod(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContactType getType() { return type; }
    public String getValue() { return value; }
}
