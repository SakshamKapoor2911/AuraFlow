package com.auraflow.notificationservice.model;

import java.time.LocalDateTime;

/**
 * Represents a notification event in the AuraFlow platform.
 */
public class Notification {
    private String id;
    private String type; // e.g., EMAIL, SMS, PUSH
    private String recipient;
    private String message;
    private LocalDateTime timestamp;
    private boolean sent;

    public Notification() {}

    public Notification(String id, String type, String recipient, String message, LocalDateTime timestamp, boolean sent) {
        this.id = id;
        this.type = type;
        this.recipient = recipient;
        this.message = message;
        this.timestamp = timestamp;
        this.sent = sent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
