package com.example.demo.exceptions;


public class NoSuchException extends RuntimeException{
    public NoSuchException(String message) {
        super(message);
    }
}