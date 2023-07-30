package com.example.springmart.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponseDto {

    String customerName;

    String customerMobileNo;

    String orderId;


    int totalValue;

    Date orderDate;

    String cardUsed;

    List<ItemResponseDto> itemResponseDtoList;
}
