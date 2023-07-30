package com.example.springmart.service.impl;

import com.example.springmart.dto.requestDto.CardRequestDto;
import com.example.springmart.dto.responseDto.CardResponseDto;
import com.example.springmart.exception.CardNumberLengthNotMatched;
import com.example.springmart.exception.CustomerNotFoundException;
import com.example.springmart.model.Card;
import com.example.springmart.model.Customer;
import com.example.springmart.repository.CardRepository;
import com.example.springmart.repository.CustomerRepository;
import com.example.springmart.service.CardService;
import com.example.springmart.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImplementation implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

        Customer customer = customerRepository.findByMobNo(cardRequestDto.getCustomerMobNo());

        if(customer == null){
            throw  new CustomerNotFoundException("Customer does not exists");
        }

        // if card number length is less than 16 throw an exception
        if(cardRequestDto.getCardNo().length() != 16){
            throw new CardNumberLengthNotMatched("Card Number should be of 16 digits");
        }

        // if customer exists and card length matches, then make a card from dto
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        // saving the parent
        Customer savedCustomer = customerRepository.save(customer); // will save both card and customer

        // prepare the card response dto
        // get the latest card
        List<Card> cardList = savedCustomer.getCards();
        Card latestCard = cardList.get(cardList.size()-1);

        CardResponseDto cardResponseDto =  CardTransformer.CardToCardResponseDto(latestCard);
        cardResponseDto.setMaskedCardNo(generateMaskedCard(latestCard.getCardNo()));

        return cardResponseDto;
    }

    public String generateMaskedCard(String cardNo) {
        int cardLength = cardNo.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cardLength-4; i++){
            sb.append("x");
        }

        sb.append(cardNo.substring(cardLength - 4));

        return sb.toString();
    }
}
