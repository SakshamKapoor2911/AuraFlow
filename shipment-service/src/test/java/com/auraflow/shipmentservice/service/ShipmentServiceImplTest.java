package com.auraflow.shipmentservice.service;

import com.auraflow.shipmentservice.model.Shipment;
import com.auraflow.shipmentservice.repository.ShipmentRepository;
import com.auraflow.shipmentservice.service.impl.ShipmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShipmentServiceImplTest {
    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentServiceImpl shipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShipment() {
        Shipment shipment = new Shipment();
        when(shipmentRepository.save(shipment)).thenReturn(shipment);
        Shipment result = shipmentService.createShipment(shipment);
        assertEquals("CREATED", result.getStatus());
        assertEquals(shipment, result);
    }

    @Test
    void testGetShipmentById() {
        Shipment shipment = new Shipment();
        shipment.setId("1");
        when(shipmentRepository.findById("1")).thenReturn(Optional.of(shipment));
        Optional<Shipment> result = shipmentService.getShipmentById("1");
        assertTrue(result.isPresent());
        assertEquals(shipment, result.get());
    }

    @Test
    void testGetAllShipments() {
        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();
        List<Shipment> shipments = Arrays.asList(shipment1, shipment2);
        when(shipmentRepository.findAll()).thenReturn(shipments);
        List<Shipment> result = shipmentService.getAllShipments();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateShipment() {
        Shipment shipment = new Shipment();
        shipment.setId("1");
        when(shipmentRepository.save(shipment)).thenReturn(shipment);
        Shipment result = shipmentService.updateShipment("1", shipment);
        assertEquals("1", result.getId());
    }

    @Test
    void testDeleteShipment() {
        doNothing().when(shipmentRepository).deleteById("1");
        shipmentService.deleteShipment("1");
        verify(shipmentRepository, times(1)).deleteById("1");
    }

    @Test
    void testTrackShipment() {
        Shipment shipment = new Shipment();
        shipment.setId("1");
        when(shipmentRepository.findById("1")).thenReturn(Optional.of(shipment));
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);
        Shipment result = shipmentService.trackShipment("1", "IN_TRANSIT");
        assertEquals("IN_TRANSIT", result.getStatus());
    }
}
