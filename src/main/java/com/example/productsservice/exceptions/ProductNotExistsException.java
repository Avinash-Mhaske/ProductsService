package com.example.productsservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "The Product that you are trying to find does not exists")
//This annotation is used to tell Spring to return a 404 status code when this exception is thrown.
//But it can be removed if we use AdviceControllers
public class ProductNotExistsException extends Exception{
    public ProductNotExistsException(String message){
        super(message);
    }
}
