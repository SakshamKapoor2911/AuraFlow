package com.auraflow.shipmentservice.controller;

import com.auraflow.shipmentservice.model.Shipment;
import com.auraflow.shipmentservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment created = shipmentService.createShipment(shipment);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable String id) {
        Optional<Shipment> shipment = shipmentService.getShipmentById(id);
        return shipment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable String id, @RequestBody Shipment shipment) {
        Shipment updated = shipmentService.updateShipment(id, shipment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable String id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/track")
    public ResponseEntity<Shipment> trackShipment(@PathVariable String id, @RequestParam String status) {
        Shipment tracked = ((com.auraflow.shipmentservice.service.impl.ShipmentServiceImpl)shipmentService).trackShipment(id, status);
        if (tracked != null) {
            return ResponseEntity.ok(tracked);
        }
        return ResponseEntity.notFound().build();
    }
}
