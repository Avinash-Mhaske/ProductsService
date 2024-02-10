package com.example.productsservice.services;

import com.example.productsservice.dtos.FakeStoreProductDto;
import com.example.productsservice.exceptions.InvalidInputException;
import com.example.productsservice.exceptions.ProductNotExistsException;
import com.example.productsservice.models.Product;

import java.util.List;

// The service layer is where the business logic of the application is implemented.
// The service layer is called by the controller layer and the service layer calls the repository layer.
// The service layer is also called the business logic layer.
// Reason why ProductService is an interface is because we can have multiple implementations of the ProductService.
// For example, we can have a ProductService implementation class that communicates with the database
// and another ProductService implementation class that communicates with a third party API (here FakeStore API).

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotExistsException, InvalidInputException;
    String deleteProduct(Long id);
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);
    Product updateProduct(Long id, Product product);
//    String replaceProductAndReturnString(Long id, Product product);
    Product addNewProduct(Product product);
}
