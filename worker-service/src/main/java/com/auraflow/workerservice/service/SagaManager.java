package com.auraflow.workerservice.service;

import org.springframework.stereotype.Service;

@Service
public class SagaManager {
    public boolean startSaga(String transactionId) {
        // Simulate starting a saga
        System.out.println("Starting saga for transaction: " + transactionId);
        return true;
    }
    public boolean compensate(String transactionId) {
        // Simulate compensating transaction
        System.out.println("Compensating transaction: " + transactionId);
        return true;
    }
}
