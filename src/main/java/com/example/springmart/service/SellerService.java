package com.example.springmart.service;


import com.example.springmart.dto.requestDto.SellerRequestDto;
import com.example.springmart.dto.responseDto.SellerResponseDto;

public interface SellerService {
    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);
}
