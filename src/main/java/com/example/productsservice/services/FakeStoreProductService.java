package com.example.productsservice.services;

import com.example.productsservice.dtos.FakeStoreProductDto;
import com.example.productsservice.exceptions.InvalidInputException;
import com.example.productsservice.exceptions.ProductNotExistsException;
import com.example.productsservice.models.Category;
import com.example.productsservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service  // @Service annotation is used to mark the class as a service provider.
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    FakeStoreProductDto fakeStoreProductDto;

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
    public Product getSingleProduct(Long id) throws ProductNotExistsException, InvalidInputException {
        // Below getForObject() method takes two parameters.
        // The first parameter is the URL of the API.
        // The second parameter is the class that we want to map the response to.
        // Here will say hey restTemplate please make a get call to this URL and the data that you get back
        // has a one on one mapping to this class
        if (id == null) {
            throw new InvalidInputException("Product id cannot be null");
        }
        FakeStoreProductDto productDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        if (productDto == null) {
            throw new ProductNotExistsException("Product with id "+id+" does not exist");
        }
        return convertFakeStoreToProduct(productDto);
    }

    @Override
    public String deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
        return "The product with id "+id+" is deleted.";
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] allProductDto = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDto dto: allProductDto) {
            answer.add(convertFakeStoreToProduct(dto));
        }
        return answer;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreToProduct(response);
    }

    @Override
    public Product updateProduct(Long id, Product product){
        fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        FakeStoreProductDto response = restTemplate.patchForObject("https://fakestoreapi.com/products/"+id, fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreToProduct(response);
    }

    @Override
    public Product addNewProduct(Product product) {
        fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        FakeStoreProductDto response=restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreToProduct(response);
    }
}


//    @Override
//    public String replaceProductAndReturnString(Long id, Product product) {
//        restTemplate.put("https://fakestoreapi.com/products/"+id, product);
//        return "The product with id "+id+" is replaced.";
//    }