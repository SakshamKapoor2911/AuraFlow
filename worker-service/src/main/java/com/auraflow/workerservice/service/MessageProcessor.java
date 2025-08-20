package com.auraflow.workerservice.service;

import org.springframework.stereotype.Service;

@Service
public class MessageProcessor {
    public boolean process(String message) {
        // Simulate processing logic
        return message != null && !message.isEmpty();
    }
}
