package com.example.springmart.dto.requestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto {


    String customerMobileNo;

    int productId;

    int requiredQuantity;  // how many quantities of this item you required

    String cardUsed;

    int cvv;



}
