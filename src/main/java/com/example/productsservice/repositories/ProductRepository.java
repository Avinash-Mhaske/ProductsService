package com.example.productsservice.repositories;

import com.example.productsservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(long id);
    // this method returns a null if the product is not found with this id.
    // There will be a nullPointerException if the product is not found with this id.
    // So need to check it in compile time if self.
    //Hence we will return here Optional<Product> findById(long id);

    Product save(Product product);
    List<Product> findAll();
    void deleteById(Long id);
}
