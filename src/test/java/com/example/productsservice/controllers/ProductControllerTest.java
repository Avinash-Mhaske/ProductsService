package com.example.productsservice.controllers;

import com.example.productsservice.models.Product;
import com.example.productsservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;
    @MockBean // This annotation is used to tell Spring to inject a mock instance of ProductService into this class.
    private ProductService productService;

    @Test
    void testProductSameAsService(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setTitle("iphone 15");
        products.add(product1);

        Product product2 = new Product();
        product2.setTitle("iphone 15 pro");
        products.add(product2);

        Product product3 = new Product();
        product3.setTitle("iphone 15 pro max");
        products.add(product3);
//        3As
//        Arrange
    when(  //when comes from the framework Mockito.
            productService.getAllProducts()
    ).thenReturn(
            products
    );

//        Act
        ResponseEntity<List<Product>> mockedResponse = productController.getAllProducts();
//        List<Product> mockedResponse = productService.getAllProducts();


//        Assert
        List<Product> productsInResponse = mockedResponse.getBody();
        assertEquals(products.size(), productsInResponse.size());

//        assertEquals(products.size(), mockedResponse.size());


    }
}