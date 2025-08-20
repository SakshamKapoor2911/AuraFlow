package com.auraflow.notificationservice.service;

import com.auraflow.notificationservice.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationTriggerService {
    private final SnsPublisherService snsPublisherService;

    @Autowired
    public NotificationTriggerService(SnsPublisherService snsPublisherService) {
        this.snsPublisherService = snsPublisherService;
    }

    public String triggerNotification(Notification notification) {
        // Format message from notification
        String message = notification.getMessage();
        String subject = notification.getType();
        return snsPublisherService.publishNotification(message, subject);
    }
}
