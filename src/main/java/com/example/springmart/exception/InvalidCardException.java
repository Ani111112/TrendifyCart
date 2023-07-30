package com.example.springmart.exception;

public class InvalidCardException extends RuntimeException{
    public InvalidCardException(String message) {
        super(message);
    }
}
