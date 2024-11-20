package com.guiconte.tracker.domain.dto;

import com.guiconte.tracker.model.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQRCodeDTO {

    private ProductDTO product;
    private String qrCodeBase64;
}
