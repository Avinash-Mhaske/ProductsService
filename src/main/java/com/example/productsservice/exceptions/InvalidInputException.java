package com.example.productsservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidInputException extends Exception{
    public InvalidInputException(String message){
        super(message);
    }
}
