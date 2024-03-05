package com.example.productsservice;

import com.example.productsservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class ProductsServiceApplicationTests {

    ProductRepository productRepository;
    @Test
    void contextLoads() {
    }
    @Test
    @Transactional
    @Commit
    void testQuery() {
//        Product product = new Product();
//        product.setTitle("Test Product");
//        product.setPrice(100);
//        productRepository.save(product);
//        List<Product> products = productRepository.findByTitleContaining("Test");
//        assertEquals(1, products.size());
    }
}
