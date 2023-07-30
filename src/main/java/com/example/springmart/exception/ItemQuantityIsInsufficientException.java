package com.example.springmart.exception;

public class ItemQuantityIsInsufficientException extends RuntimeException{
    public ItemQuantityIsInsufficientException(String message) {
        super(message);
    }
}
