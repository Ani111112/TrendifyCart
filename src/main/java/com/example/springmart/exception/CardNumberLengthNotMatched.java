package com.example.springmart.exception;

public class CardNumberLengthNotMatched extends RuntimeException{
    public CardNumberLengthNotMatched(String message) {
        super(message);
    }
}
