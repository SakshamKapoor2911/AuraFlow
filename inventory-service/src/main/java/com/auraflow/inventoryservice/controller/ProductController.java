package com.auraflow.inventoryservice.controller;

import com.auraflow.inventoryservice.model.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// This REST controller exposes HTTP endpoints for managing products.
// For now, it only supports creating a product (POST).
@RestController
@RequestMapping("/products") // All endpoints will start with /products
public class ProductController {

    // Inject the ProductRepository to interact with DynamoDB
    private final com.auraflow.inventoryservice.repository.ProductRepository productRepository;
    private final com.auraflow.inventoryservice.event.InventoryEventPublisher eventPublisher;

    // Constructor injection for repository and event publisher
    public ProductController(com.auraflow.inventoryservice.repository.ProductRepository productRepository,
                            com.auraflow.inventoryservice.event.InventoryEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    // POST /products
    // Accepts a Product object in the request body (JSON)
    // Saves the product to DynamoDB and returns it
    // POST /products - Create a new product
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
        // Publish event to SQS
        eventPublisher.publishEvent("PRODUCT_CREATED", product.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // GET /products/{id} - Retrieve a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        Product found = productRepository.getById(id);
        if (found == null) {
            // If not found, return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(found);
    }

    // PUT /products/{id} - Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        Product existing = productRepository.getById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        product.setId(id);
        productRepository.save(product);
        // Publish event to SQS
        eventPublisher.publishEvent("PRODUCT_UPDATED", id);
        return ResponseEntity.ok(product);
    }

    // DELETE /products/{id} - Remove a product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        Product existing = productRepository.getById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.deleteById(id);
        // Publish event to SQS
        eventPublisher.publishEvent("PRODUCT_DELETED", id);
        return ResponseEntity.ok("Product deleted");
    }
    // Add deleteById method to ProductRepository
    // Handle validation errors and return user-friendly messages
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationError(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMsg);
    }
}