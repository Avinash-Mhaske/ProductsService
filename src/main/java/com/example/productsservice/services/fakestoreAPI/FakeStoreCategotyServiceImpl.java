package com.example.productsservice.services.fakestoreAPI;

import com.example.productsservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreCategoryService")
public class FakeStoreCategotyServiceImpl implements CategoryService {

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreCategotyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public String getCategoryById(Long id) {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories"+id, String.class);
    }

    @Override
    public String[] getAllCategories() {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
    }
}
