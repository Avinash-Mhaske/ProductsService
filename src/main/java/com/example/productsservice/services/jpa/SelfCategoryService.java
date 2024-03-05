package com.example.productsservice.services.jpa;

import com.example.productsservice.models.Product;
import com.example.productsservice.repositories.CategoryRepository;
import com.example.productsservice.repositories.ProductRepository;
import com.example.productsservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {
    CategoryRepository categoryRepo;

    @Autowired
    public SelfCategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public String getCategoryById(Long id) {
        return null;
    }

    @Override
    public String[] getAllCategories() {
        return null;
    }
}