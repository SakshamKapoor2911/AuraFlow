package com.auraflow.shipmentservice.repository;

import com.auraflow.shipmentservice.model.Shipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, String> {
    // Additional query methods if needed
}
