package com.example.productsservice.controllers;

import com.example.productsservice.exceptions.InvalidInputException;
import com.example.productsservice.exceptions.ProductNotExistsException;
import com.example.productsservice.models.Product;
import com.example.productsservice.services.ProductService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistsException, InvalidInputException {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
//        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product AddNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }



}
    //Demo of ResponseEntity with method returning Void.
//@DeleteMapping()
//public ResponseEntity<Void> deleteAProduct(@PathVariable("id") Long id) {
//    return new ResponseEntity<>(HttpStatus.OK);
//}

    //Demo of returning a String when a retTemplate method returns nothing.
//    @PutMapping("/{id}")
//    public String replaceProductAndReturnString(@PathVariable("id") Long id, @RequestBody Product product){
//        return productService.replaceProductAndReturnString(id,product);
//    }

// a controller is a class that handles HTTP requests.
// The controller layer is the layer that handles HTTP requests.
// `@RestController` annotation is used to mark the class as a controller class.
// `@RequestMapping` annotation is used to map a URL to a method.
// `@GetMapping` annotation is used to map a GET request to a method.
// `@PostMapping` annotation is used to map a POST request to a method.