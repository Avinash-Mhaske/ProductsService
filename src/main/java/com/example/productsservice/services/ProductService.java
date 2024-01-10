package com.example.productsservice.services;

import com.example.productsservice.models.Product;

// The service layer is where the business logic of the application is implemented.
// The service layer is called by the controller layer and the service layer calls the repository layer.
// The service layer is also called the business logic layer.
// Reason why ProductService is an interface is because we can have multiple implementations of the ProductService.
// For example, we can have a ProductService implementation class that communicates with the database
// and another ProductService implementation class that communicates with a third party API (here FakeStore API).

public interface ProductService {
    Product getSingleProduct(Long id);
    String deleteProduct(Long id);
}
