package com.example.springmart.dto.responseDto;

import com.example.springmart.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemResponseDto {

    String itemName;

    int itemPrice;

    int quantityAdded;

    ProductCategory category;
}
