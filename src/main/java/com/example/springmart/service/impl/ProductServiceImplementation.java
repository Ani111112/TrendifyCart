package com.example.springmart.service.impl;

import com.example.springmart.Enum.ProductCategory;
import com.example.springmart.dto.requestDto.ProductRequestDto;
import com.example.springmart.dto.responseDto.ProductResponseDto;
import com.example.springmart.exception.SellerNotFoundException;
import com.example.springmart.model.Product;
import com.example.springmart.model.Seller;
import com.example.springmart.repository.ProductRepository;
import com.example.springmart.repository.SellerRepository;
import com.example.springmart.service.ProductService;
import com.example.springmart.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        // first check that seller exists or not
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());

        // if seller not exists then throw exception
        if (seller == null){
            throw new SellerNotFoundException("email does not exists");
        }

        // Now add the product by converting dto to product entity
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);

        // after this we have to set the seller also in the product , which was not in our dto
        product.setSeller(seller);
        // after setting the seller to product , just make sure that, if any attributes in seller is changing , then change that attribute
        seller.getProducts().add(product);

        // now save the parent entity
        Seller savedSeller = sellerRepository.save(seller); // save both product and seller

        // Now Prepare the response
        List<Product> productList = savedSeller.getProducts(); // saved seller se product list mangwai
        Product latestProduct = productList.get(productList.size()-1);   // fir uss list se latest product nikala

        return ProductTransformer.ProductToProductResponseDto(latestProduct);
    }

    @Override
    public List<ProductResponseDto> getProductByCategoryAndPriceGreaterThan(int price, ProductCategory category) {

        List<Product> productList = productRepository.getProductByCategoryAndPriceGreaterThan(price,category);

        // prepare the list of response
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for (Product product : productList){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        // return the response list
        return productResponseDtos;
    }


}
