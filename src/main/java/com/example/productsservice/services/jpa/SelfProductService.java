package com.example.productsservice.services.jpa;

import com.example.productsservice.exceptions.InvalidInputException;
import com.example.productsservice.exceptions.ProductNotExistsException;
import com.example.productsservice.models.Category;
import com.example.productsservice.models.Product;
import com.example.productsservice.repositories.CategoryRepository;
import com.example.productsservice.repositories.ProductRepository;
import com.example.productsservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    ProductRepository productRepo;
    CategoryRepository categoryRepo;
    @Autowired
    public SelfProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistsException, InvalidInputException{
        Optional<Product> productOptional = productRepo.findById(id);
        if (id == null) {
            throw new InvalidInputException("Product id cannot be null");
        }
        if (productOptional.isEmpty()) {
            throw new ProductNotExistsException("Product with id " + id + " does not exist");
        }
        return productOptional.get();
    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepo.findByName(product.getCategory().getName());
        if (categoryOptional.isEmpty()) {
            product.setCategory(categoryRepo.save(product.getCategory()));
        }else{
            product.setCategory(categoryOptional.get());
        }
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException();
        }
        Product savedProduct= productOptional.get();
        if (product.getTitle() != null) {
            savedProduct.setTitle(product.getTitle());
        }
        if (product.getPrice() != null) {
            savedProduct.setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
            savedProduct.setDescription(product.getDescription());
        }
        if (product.getImageUrl() != null) {
            savedProduct.setImageUrl(product.getImageUrl());
        }
        return productRepo.save(savedProduct);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> productOptional= productRepo.findById(id);
        if(productOptional.isEmpty()) {
            throw new RuntimeException();
        }
        Product savedProduct=productOptional.get();
        savedProduct.setTitle(product.getTitle());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setImageUrl(product.getImageUrl());
//      savedProduct.getCategory().setName(product.getCategory().getName());
//      Categary cannot be updated, because if the category that is asked to be updated
//      is not present in the database, It will not be added to database.
//      If we want to update it any ways then we need to write the logic for it, same as addNewProduct.

        return productRepo.save(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
