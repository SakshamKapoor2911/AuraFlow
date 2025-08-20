package com.auraflow.workerservice.service;

import org.springframework.stereotype.Service;

@Service
public class ErrorHandler {
    public void handleError(String message, Exception e) {
        // Simulate error handling and dead letter queue logic
        System.err.println("Error processing message: " + message);
        System.err.println("Exception: " + e.getMessage());
    }
}
