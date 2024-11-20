package com.guiconte.tracker.service;

import com.guiconte.tracker.domain.dto.ProductQRCodeDTO;
import com.guiconte.tracker.domain.model.Product;
import com.guiconte.tracker.model.ProductDTO;
import com.guiconte.tracker.repository.ProductRepository;
import com.guiconte.tracker.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        productRepository.save(product);
    }

    public List<ProductDTO> findProducts() {
        List<Product> productList = productRepository.findAll();
        return productMapper.productToProductDTO(productList);
    }

    public ProductDTO findProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return productMapper.productToProductDTO(product);
    }

    public ProductQRCodeDTO getProductQRCode(ProductDTO productDTO) {
        return productMapper.productToProductWithQRCode(productDTO);
    }

    public List<ProductQRCodeDTO> getProductQRCode(List<ProductDTO> productDTO) {
        List<ProductQRCodeDTO> productQRCodeDTO = new ArrayList<>();
        for (ProductDTO product : productDTO){
            productQRCodeDTO.add(getProductQRCode(product));
        }
        return productQRCodeDTO;
    }
}
