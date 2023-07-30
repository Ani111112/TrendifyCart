package com.example.springmart.transformer;

import com.example.springmart.dto.requestDto.CardRequestDto;
import com.example.springmart.dto.responseDto.CardResponseDto;
import com.example.springmart.model.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardType(card.getCardType())
                .validTill(card.getValidTill())
                .build();
    }
}
