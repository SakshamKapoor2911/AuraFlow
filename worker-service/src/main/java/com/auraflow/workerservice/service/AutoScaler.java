package com.auraflow.workerservice.service;

import org.springframework.stereotype.Service;

@Service
public class AutoScaler {
    public void scale(int queueDepth) {
        // Simulate auto-scaling logic
        if (queueDepth > 100) {
            System.out.println("Scaling up worker instances...");
        } else {
            System.out.println("Scaling down worker instances...");
        }
    }
}
