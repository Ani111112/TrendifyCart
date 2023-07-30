package com.example.springmart.service;

import com.example.springmart.Enum.ProductCategory;
import com.example.springmart.dto.requestDto.ProductRequestDto;
import com.example.springmart.dto.responseDto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getProductByCategoryAndPriceGreaterThan(int price, ProductCategory category);
}
