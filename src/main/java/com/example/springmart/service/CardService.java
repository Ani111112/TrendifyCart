package com.example.springmart.service;

import com.example.springmart.dto.requestDto.CardRequestDto;
import com.example.springmart.dto.responseDto.CardResponseDto;

public interface CardService {
    CardResponseDto addCard(CardRequestDto cardRequestDto);

    String generateMaskedCard(String cardNo);
}
