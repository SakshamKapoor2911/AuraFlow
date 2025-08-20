package com.auraflow.workerservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkerServiceTests {
    @Autowired
    private EventSourcingManager eventSourcingManager;
    @Test
    void testMessageProcessing() {
        MessageProcessor processor = new MessageProcessor();
        assertTrue(processor.process("test message"));
        assertFalse(processor.process(""));
    }

    @Test
    void testErrorHandler() {
        ErrorHandler handler = new ErrorHandler();
        handler.handleError("msg", new Exception("fail"));
        // No exception thrown means pass
    }
        @Test
        void benchmarkEventProcessing() {
            int eventCount = 10000;
            long start = System.currentTimeMillis();
            for (int i = 0; i < eventCount; i++) {
                eventSourcingManager.recordEvent("Event #" + i);
            }
            long end = System.currentTimeMillis();
            long durationMs = end - start;
            double eventsPerMinute = (eventCount * 60000.0) / durationMs;
            System.out.println("Processed " + eventCount + " events in " + durationMs + " ms (" + eventsPerMinute + " events/minute)");
            assertTrue(eventsPerMinute > 3000, "Throughput should exceed 3,000 events/minute (simulated DynamoDB latency)");
        }

    @Test
    void testAutoScaler() {
        AutoScaler scaler = new AutoScaler();
        scaler.scale(150); // Should print scaling up
        scaler.scale(50);  // Should print scaling down
    }
}
