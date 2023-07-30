package com.example.springmart.dto.responseDto;

import com.example.springmart.Enum.ProductCategory;
import com.example.springmart.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponseDto {

    String sellerName;

    String productName;

    Integer price;

    ProductCategory category;

    Integer availableQuantity;

    ProductStatus productStatus;

}
