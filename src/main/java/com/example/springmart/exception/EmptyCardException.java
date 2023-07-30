package com.example.springmart.exception;

public class EmptyCardException extends RuntimeException{
    public EmptyCardException(String message) {
        super(message);
    }
}
