package com.bhaskar.urlshortner.services.product;

import com.bhaskar.urlshortner.model.common.Category;
import com.bhaskar.urlshortner.model.product.Product;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductServices {
    ResponseEntity<?> addProduct(Product product);
    ResponseEntity<?> addProductList(List<Product> productList);
    ResponseEntity<?> removeProduct(Long productId);
    Optional<Product> getProductById(Long productId);
    BigDecimal getProductPrice(Product product);
    BigDecimal getProductPriceByProductId(Long productId);
    Category getProductCategory(Product product);
    String  getProductName(Product product);
}
