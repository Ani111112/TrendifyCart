package com.example.springmart.transformer;

import com.example.springmart.dto.responseDto.ItemResponseDto;
import com.example.springmart.dto.responseDto.OrderResponseDto;
import com.example.springmart.model.Item;
import com.example.springmart.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {
    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity savedOrder) {

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for (Item item : savedOrder.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderId(savedOrder.getOrderNo())
                .totalValue(savedOrder.getTotalValue())
                .cardUsed(savedOrder.getCardUsed())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobileNo(savedOrder.getCustomer().getMobNo())
                .orderDate(savedOrder.getOrderDate())
                .itemResponseDtoList(itemResponseDtoList)
                .build();
    }
}
