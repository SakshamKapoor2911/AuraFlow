package com.auraflow.inventoryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsClient;

// Configuration for AWS SQS client
@Configuration
public class SqsConfig {
    @Bean
    public SqsClient sqsClient() {
        // Connect to localstack or AWS SQS endpoint
        return SqsClient.builder()
                .endpointOverride(java.net.URI.create("http://localhost:4566")) // LocalStack SQS
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                .build();
    }
}
