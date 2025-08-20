package com.auraflow.workerservice.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventSourcingManager {
    private DynamoDbClient dynamoDbClient;
    private final String tableName = "Events";

    @PostConstruct
    public void init() {
        dynamoDbClient = DynamoDbClient.builder()
            .endpointOverride(URI.create("http://localhost:8000")) // DynamoDB Local
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create("test", "test")))
            .overrideConfiguration(ClientOverrideConfiguration.builder().build())
            .build();
    }

    public void recordEvent(String event) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("EventId", AttributeValue.builder().s(String.valueOf(System.currentTimeMillis())).build());
        item.put("EventData", AttributeValue.builder().s(event).build());

        PutItemRequest request = PutItemRequest.builder()
            .tableName(tableName)
            .item(item)
            .build();

        dynamoDbClient.putItem(request);
        System.out.println("Recorded event in DynamoDB Local: " + event);
    }
}
