package com.auraflow.shipmentservice.service.impl;

import com.auraflow.shipmentservice.model.Shipment;
import com.auraflow.shipmentservice.repository.ShipmentRepository;
import com.auraflow.shipmentservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment createShipment(Shipment shipment) {
        // Business logic: validate shipment, set initial status
        shipment.setStatus("CREATED");
        return shipmentRepository.save(shipment);
    }

    @Override
    public Optional<Shipment> getShipmentById(String id) {
        return shipmentRepository.findById(id);
    }

    @Override
    public List<Shipment> getAllShipments() {
        return StreamSupport.stream(shipmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Shipment updateShipment(String id, Shipment shipment) {
        shipment.setId(id);
        // Business logic: update status, validate changes
        return shipmentRepository.save(shipment);
    }

    @Override
    public void deleteShipment(String id) {
        shipmentRepository.deleteById(id);
    }

    // Business logic for shipment tracking
    public Shipment trackShipment(String id, String newStatus) {
        Optional<Shipment> shipmentOpt = shipmentRepository.findById(id);
        if (shipmentOpt.isPresent()) {
            Shipment shipment = shipmentOpt.get();
            shipment.setStatus(newStatus);
            return shipmentRepository.save(shipment);
        }
        return null;
    }
}
