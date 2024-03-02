package com.example.productsservice.services;

import com.example.productsservice.exceptions.InvalidInputException;
import com.example.productsservice.exceptions.ProductNotExistsException;
import com.example.productsservice.models.Category;
import com.example.productsservice.models.Product;

import java.util.List;

public interface CategoryService {
    String getCategoryById(Long id);
    String[] getAllCategories();
}
