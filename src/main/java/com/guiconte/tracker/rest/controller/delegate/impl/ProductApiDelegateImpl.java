package com.guiconte.tracker.rest.controller.delegate.impl;

import com.guiconte.tracker.api.ProductApiDelegate;
import com.guiconte.tracker.model.ProductDTO;
import com.guiconte.tracker.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductApiDelegateImpl implements ProductApiDelegate {

    private final ProductService productService;

    @Override
    public ResponseEntity<Void> addProduct(ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<ProductDTO>> findProducts() {
        List<ProductDTO> products = productService.findProducts();
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(UUID productId) {
        ProductDTO product = productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }
}
