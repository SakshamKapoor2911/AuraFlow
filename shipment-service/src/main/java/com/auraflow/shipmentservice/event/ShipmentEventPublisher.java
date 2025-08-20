package com.auraflow.shipmentservice.event;

import com.auraflow.shipmentservice.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventPublisher {
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public ShipmentEventPublisher(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public void publishShipmentEvent(Shipment shipment) {
        queueMessagingTemplate.convertAndSend("shipment-events-queue", shipment);
    }
}
