package com.example.springmart.transformer;

import com.example.springmart.dto.requestDto.ItemRequestDto;
import com.example.springmart.dto.responseDto.ItemResponseDto;
import com.example.springmart.model.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(int requiredQuantity){
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .itemName(item.getProduct().getName())
                .itemPrice(item.getProduct().getPrice())
                .quantityAdded(item.getRequiredQuantity())
                .category(item.getProduct().getCategory())
                .build();
    }
}
