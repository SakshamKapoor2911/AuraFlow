package com.auraflow.notificationservice.service;

import com.auraflow.notificationservice.model.Notification;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTriggerServiceTest {
    @Test
    void testTriggerNotification() {
        SnsPublisherService mockPublisher = Mockito.mock(SnsPublisherService.class);
        Mockito.when(mockPublisher.publishNotification(Mockito.anyString(), Mockito.anyString())).thenReturn("msg-id");

        NotificationTriggerService triggerService = new NotificationTriggerService(mockPublisher);
        Notification notification = new Notification();
        notification.setMessage("Test message");
        notification.setType("EMAIL");
        String result = triggerService.triggerNotification(notification);
        assertEquals("msg-id", result);
    }
}
