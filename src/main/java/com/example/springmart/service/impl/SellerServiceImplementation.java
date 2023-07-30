package com.example.springmart.service.impl;

import com.example.springmart.dto.requestDto.SellerRequestDto;
import com.example.springmart.dto.responseDto.SellerResponseDto;
import com.example.springmart.model.Seller;
import com.example.springmart.repository.SellerRepository;
import com.example.springmart.service.SellerService;
import com.example.springmart.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImplementation implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        // convert the request dto to seller
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        // now save the seller
        Seller savedSeller = sellerRepository.save(seller);

        // Now prepare the response dto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}
