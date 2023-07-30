package com.example.springmart.controller;

import com.example.springmart.Enum.ProductCategory;
import com.example.springmart.dto.requestDto.ProductRequestDto;
import com.example.springmart.dto.responseDto.ProductResponseDto;
import com.example.springmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    // Add product
    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            ProductResponseDto productResponse =  productService.addProduct(productRequestDto);
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get product of certain category and and price greater than
    @GetMapping("/get_by_category_andPrice_greater_than")
    public ResponseEntity<List<ProductResponseDto>> getProductByCategoryAndPriceGreaterThan(@RequestParam("price") int price, @RequestParam("productCategory") ProductCategory category){
        List<ProductResponseDto> productList = productService.getProductByCategoryAndPriceGreaterThan(price, category);
        return new ResponseEntity<>(productList,HttpStatus.CREATED);
    }

}
