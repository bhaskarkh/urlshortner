package com.bhaskar.urlshortner.repository.product;

import com.bhaskar.urlshortner.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
