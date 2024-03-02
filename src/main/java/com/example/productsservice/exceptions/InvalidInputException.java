package com.example.productsservice.exceptions;


public class InvalidInputException extends Exception{
    private String message;
    public InvalidInputException(String message){
        super(message);
    }
}
