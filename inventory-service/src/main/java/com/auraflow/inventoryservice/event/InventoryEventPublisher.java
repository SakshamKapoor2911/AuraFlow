package com.auraflow.inventoryservice.event;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

// Publishes inventory events to SQS
@Component
public class InventoryEventPublisher {
    private final SqsClient sqsClient;
    private final String queueUrl = "http://localhost:4566/000000000000/inventory-events"; // Update to your queue URL

    public InventoryEventPublisher(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    // Publishes a message to SQS
    public void publishEvent(String eventType, String productId) {
        String messageBody = String.format("{\"eventType\":\"%s\",\"productId\":\"%s\"}", eventType, productId);
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();
        sqsClient.sendMessage(request);
    }
}
