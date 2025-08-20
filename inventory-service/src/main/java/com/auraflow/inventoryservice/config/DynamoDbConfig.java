package com.auraflow.inventoryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

// Configuration class to set up DynamoDB clients for the application
@Configuration
public class DynamoDbConfig {
    // Bean to create a DynamoDbClient (connects to local DynamoDB for dev)
    @Bean
    public DynamoDbClient dynamoDbClient() {
        // endpointOverride points to local DynamoDB instance
        return DynamoDbClient.builder()
                .endpointOverride(java.net.URI.create("http://localhost:8000")) // Local DynamoDB
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                .build();
    }

    // Bean to create an enhanced client for easier object mapping
    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }
}
