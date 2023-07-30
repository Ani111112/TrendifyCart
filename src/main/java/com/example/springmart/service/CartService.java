package com.example.springmart.service;

import com.example.springmart.dto.requestDto.CheckOutCartRequestDto;
import com.example.springmart.dto.requestDto.ItemRequestDto;
import com.example.springmart.dto.responseDto.CartResponseDto;
import com.example.springmart.dto.responseDto.OrderResponseDto;
import com.example.springmart.model.Item;

public interface CartService {
    CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item);

    OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto);
}
