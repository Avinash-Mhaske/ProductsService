package com.example.productsservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionsDto {

    private String message;
    private String details; //This is the message that we want to send to the client.
}
