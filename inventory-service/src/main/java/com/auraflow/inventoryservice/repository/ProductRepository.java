package com.auraflow.inventoryservice.repository;

import com.auraflow.inventoryservice.model.Product;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import javax.annotation.PostConstruct;

// Repository class to interact with DynamoDB for Product objects
@Repository
public class ProductRepository {
    // DynamoDB table object for Product
    private DynamoDbTable<Product> productTable;

    // Injected DynamoDB enhanced client
    private final DynamoDbEnhancedClient enhancedClient;

    // Table name (should match your AWS CDK or local setup)
    private final String tableName = "Products";

    // Constructor injection of the enhanced client
    public ProductRepository(DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
    }

    // Initialize the table object after construction
    @PostConstruct
    public void init() {
        productTable = enhancedClient.table(tableName, TableSchema.fromBean(Product.class));
    }

    // Save a product to DynamoDB
    public void save(Product product) {
        productTable.putItem(product);
    }

    // Get a product by ID
    public Product getById(String id) {
        // Uses the builder pattern to create a Product with only the ID
        return productTable.getItem(Product.builder().id(id).build());
    }

    // Delete a product by ID
    public void deleteById(String id) {
        productTable.deleteItem(Product.builder().id(id).build());
    }
}
