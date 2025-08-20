package com.auraflow.notificationservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class SnsPublisherServiceTest {
    @Test
    void testPublishNotification() {
        SnsClient mockClient = Mockito.mock(SnsClient.class);
        PublishResponse mockResponse = PublishResponse.builder().messageId("test-id").build();
        Mockito.when(mockClient.publish(any(PublishRequest.class))).thenReturn(mockResponse);

        SnsPublisherService service = new SnsPublisherService(mockClient);
        String result = service.publishNotification("Hello", "TestSubject");
        assertEquals("test-id", result);
    }
}
