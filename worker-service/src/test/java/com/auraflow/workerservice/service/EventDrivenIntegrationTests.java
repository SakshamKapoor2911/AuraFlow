package com.auraflow.workerservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventDrivenIntegrationTests {
    @Autowired
    private EventSourcingManager eventManager;
    @Test
    void testSagaManager() {
        SagaManager saga = new SagaManager();
        assertTrue(saga.startSaga("txn1"));
        assertTrue(saga.compensate("txn1"));
    }

    @Test
    void testEventSourcingManager() {
        eventManager.recordEvent("event1");
        // No exception thrown means pass
    }
}
