package com.auraflow.notificationservice.service;

import com.auraflow.notificationservice.model.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Optional<Notification> getNotificationById(String id);
    List<Notification> getAllNotifications();
    Notification updateNotification(String id, Notification notification);
    void deleteNotification(String id);
    // Add business logic methods as needed
}
