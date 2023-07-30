package com.example.springmart.transformer;

import com.example.springmart.dto.responseDto.CartResponseDto;
import com.example.springmart.dto.responseDto.ItemResponseDto;
import com.example.springmart.model.Cart;
import com.example.springmart.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){

        // prepare the item response dto list
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for (Item item : cart.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartTotal(cart.getCartTotal())
                .items(itemResponseDtoList)
                .build();
    }
}
