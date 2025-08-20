package com.auraflow.inventoryservice.service.impl;

import com.auraflow.inventoryservice.model.Product;
import com.auraflow.inventoryservice.repository.ProductRepository;
import com.auraflow.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        // Business logic: e.g., check for duplicate SKU, validate fields
        productRepository.save(product);
        return product;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        Product product = productRepository.getById(id);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAllProducts() {
        // DynamoDbTable does not support findAll directly; implement as needed
        // For now, return empty list or implement scan logic if required
        return List.of();
    }

    @Override
    public Product updateProduct(String id, Product product) {
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    // Add more business logic methods as needed
}
