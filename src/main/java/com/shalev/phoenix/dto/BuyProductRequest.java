package com.shalev.phoenix.dto;

public class BuyProductRequest {

    private Long clientId;
    private Long productId;

    public Long getClientId() { return clientId;}

    public void setClientId(Long clientId) { this.clientId = clientId;}

    public Long getProductId() { return productId;}

    public void setProductId(Long productId) { this.productId = productId; }
}
