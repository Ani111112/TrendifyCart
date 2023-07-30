package com.example.springmart.controller;

import com.example.springmart.dto.requestDto.CheckOutCartRequestDto;
import com.example.springmart.dto.requestDto.ItemRequestDto;
import com.example.springmart.dto.responseDto.CartResponseDto;
import com.example.springmart.dto.responseDto.OrderResponseDto;
import com.example.springmart.model.Item;
import com.example.springmart.service.CartService;
import com.example.springmart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;




    // add item to cart
    @PostMapping("/add_item_toCart")
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            // first step is to create an item from item request dto
            Item item = itemService.createItem(itemRequestDto);

            // add the item to cart
            CartResponseDto cartResponseDto = cartService.addItemToCart(itemRequestDto,item);

            return new ResponseEntity<>(cartResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    //checkout cart
    @PostMapping("/check_out_cart")
    public ResponseEntity<OrderResponseDto> checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto){

        try {
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkOutCartRequestDto);
            return new ResponseEntity<>(orderResponseDto,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
