package com.auraflow.workerservice.service;

import org.springframework.stereotype.Service;

@Service
public class SqsMessageConsumer {
    public void consume(String message) {
        // Simulate message processing
        System.out.println("Processing message: " + message);
    }
}
