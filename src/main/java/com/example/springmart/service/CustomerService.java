package com.example.springmart.service;

import com.example.springmart.dto.requestDto.CustomerRequestDto;
import com.example.springmart.dto.responseDto.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

}
