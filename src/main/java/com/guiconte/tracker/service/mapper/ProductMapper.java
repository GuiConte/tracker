package com.guiconte.tracker.service.mapper;

import com.guiconte.tracker.domain.dto.ProductQRCodeDTO;
import com.guiconte.tracker.domain.model.Product;
import com.guiconte.tracker.model.ProductDTO;
import com.guiconte.tracker.service.QRCodeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    Product productDTOToProduct(ProductDTO product);

    List<Product> productDTOToProduct(List<ProductDTO> productDTOList);

    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productToProductDTO(List<Product> product);

    @Mapping(source = "product", target = "product")
    @Mapping(source = "product.id", target = "qrCodeBase64", qualifiedByName = "generateQRCode")
    ProductQRCodeDTO productToProductWithQRCode(ProductDTO product);

    @Named("generateQRCode")
    default String generateQRCode(String productId) {
        QRCodeService qrCodeService = new QRCodeService();
        return qrCodeService.generateQRCodeBase64(productId);
    }
}
