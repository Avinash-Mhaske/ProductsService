package com.example.productsservice.services;

import com.example.productsservice.dtos.FakeStoreProductDto;
import com.example.productsservice.models.Category;
import com.example.productsservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service  // @Service annotation is used to mark the class as a service provider.
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    @Autowired  //This annotation is used to inject the dependency automatically.
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
// To use a bean in a class, we have to create a constructor of that class,
// and pass the bean as a parameter to the constructor. Then annotate it with @Autowired.

    private Product convertFakeStoreToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product=new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        // Below getForObject() method takes two parameters.
        // The first parameter is the URL of the API.
        // The second parameter is the class that we want to map the response to.
        //Here will say hey restTemplate please make a get call to this URL and the data that you get back
        // has a one on one mapping to this class
        FakeStoreProductDto productDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        return convertFakeStoreToProduct(productDto);
    }

    @Override
    public String deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
        return "The product with id "+id+" is deleted.";
    }
}
