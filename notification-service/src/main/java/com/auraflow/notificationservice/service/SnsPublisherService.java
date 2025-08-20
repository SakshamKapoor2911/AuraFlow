package com.auraflow.notificationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class SnsPublisherService {
    private final SnsClient snsClient;

    @Value("${aws.sns.topic.arn}")
    private String topicArn;

    public SnsPublisherService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public String publishNotification(String message, String subject) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .subject(subject)
                .build();
        PublishResponse response = snsClient.publish(request);
        return response.messageId();
    }
}
