package com.example.springmart.exception;

public class SellerNotFoundException extends RuntimeException{

    public SellerNotFoundException(String message){
        super(message);
    }
}
