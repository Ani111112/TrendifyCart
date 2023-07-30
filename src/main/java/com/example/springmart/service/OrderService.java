package com.example.springmart.service;

import com.example.springmart.dto.requestDto.CheckOutCartRequestDto;
import com.example.springmart.dto.requestDto.OrderRequestDto;
import com.example.springmart.dto.responseDto.OrderResponseDto;
import com.example.springmart.model.Card;
import com.example.springmart.model.Cart;
import com.example.springmart.model.OrderEntity;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);


    OrderEntity placeOrder(Cart cart, Card card);

    void sendEmail(OrderEntity savedOrder);
}
