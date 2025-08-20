package com.auraflow.shipmentservice.model;

import java.time.LocalDateTime;

public class Shipment {
    private String id;
    private String orderId;
    private String status;
    private LocalDateTime shippedAt;
    private String destination;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getShippedAt() { return shippedAt; }
    public void setShippedAt(LocalDateTime shippedAt) { this.shippedAt = shippedAt; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
}
