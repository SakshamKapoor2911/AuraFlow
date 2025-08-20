package com.auraflow.inventoryservice.service;

import com.auraflow.inventoryservice.model.Product;
import com.auraflow.inventoryservice.repository.ProductRepository;
import com.auraflow.inventoryservice.service.impl.ProductServiceImpl;
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

class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        // save returns void, so just verify call
        doNothing().when(productRepository).save(product);
        Product result = productService.createProduct(product);
        assertEquals(product, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId("1");
        when(productRepository.getById("1")).thenReturn(product);
        Optional<Product> result = productService.getProductById("1");
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void testGetAllProducts() {
        // getAllProducts returns empty list for now
        List<Product> result = productService.getAllProducts();
        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setId("1");
        doNothing().when(productRepository).save(product);
        Product result = productService.updateProduct("1", product);
        assertEquals("1", result.getId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById("1");
        productService.deleteProduct("1");
        verify(productRepository, times(1)).deleteById("1");
    }
}
