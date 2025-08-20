package com.auraflow.shipmentservice.service;

import com.auraflow.shipmentservice.model.Shipment;
import java.util.List;
import java.util.Optional;

public interface ShipmentService {
    Shipment createShipment(Shipment shipment);
    Optional<Shipment> getShipmentById(String id);
    List<Shipment> getAllShipments();
    Shipment updateShipment(String id, Shipment shipment);
    void deleteShipment(String id);
    // Add business logic methods as needed
}
