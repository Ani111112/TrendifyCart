package com.example.springmart.dto.requestDto;

import com.example.springmart.Enum.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {

    String productName;

    Integer price;

    ProductCategory category;

    Integer availableQuantity;

    String sellerEmailId;
}
