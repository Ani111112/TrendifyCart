package com.example.springmart.transformer;

import com.example.springmart.Enum.ProductStatus;
import com.example.springmart.dto.requestDto.ProductRequestDto;
import com.example.springmart.dto.responseDto.ProductResponseDto;
import com.example.springmart.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getName())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
