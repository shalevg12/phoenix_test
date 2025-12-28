package com.shalev.phoenix.dto;

public class CreateClientRequest {
    private Long id;
    private String name;
    // שים לב: מחרוזת, לא Enum – נוח יותר לטיפול בשגיאות
    private String contactType;
    private String contactValue;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getContactType() { return contactType;}

    public void setContactType(String contactType) { this.contactType = contactType;}

    public String getContactValue() { return contactValue; }

    public void setContactValue(String contactValue) { this.contactValue = contactValue; }
}
