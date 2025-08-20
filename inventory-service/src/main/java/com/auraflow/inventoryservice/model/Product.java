package com.auraflow.inventoryservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

// This class represents a product in your inventory system.
// It will be used to transfer product data between layers and over the network.
public class Product {
    // DynamoDB requires a default constructor and getters/setters
    @NotBlank(message = "Product ID must not be blank")
    private String id;      // Unique identifier for the product

    @NotBlank(message = "Product name must not be blank")
    private String name;    // Name of the product

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be zero or positive")
    private Integer quantity;   // Quantity available in inventory

    public Product() {}

    public Product(String id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Builder pattern for DynamoDB Enhanced Client
    public static Builder builder() {
        return new Builder();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    // Builder class for Product
    public static class Builder {
        private String id;
        private String name;
        private int quantity;

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder quantity(int quantity) { this.quantity = quantity; return this; }
        public Product build() { return new Product(id, name, quantity); }
    }
}