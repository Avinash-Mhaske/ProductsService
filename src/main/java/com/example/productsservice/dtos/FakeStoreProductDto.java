package com.example.productsservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

//A DTO (data transfer Object) is a data type that you send outside(i.e 3rd party API)
// or receive from outside your application.
// This class is used to map the response from the FakeStore API.
@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
    //The dto is there to replicate how the API is sending data to us.
    //We have to provide exact same name of attributes as the API is sending to us.
    //Also exact same data type of attributes as the API is sending to us.
    // All the above data types are the same as the FakeStore API response.
    // We don't need to map all the fields from the FakeStore API response.
    // We only need to map the fields that we need.

}
