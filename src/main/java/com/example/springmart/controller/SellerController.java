package com.example.springmart.controller;

import com.example.springmart.dto.requestDto.SellerRequestDto;
import com.example.springmart.dto.responseDto.SellerResponseDto;
import com.example.springmart.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    // Add Seller
    @PostMapping("/add")
    public ResponseEntity<SellerResponseDto> addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponse = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity<>(sellerResponse, HttpStatus.CREATED);
    }
}
