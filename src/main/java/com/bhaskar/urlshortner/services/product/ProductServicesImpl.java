package com.bhaskar.urlshortner.services.product;

import com.bhaskar.urlshortner.exception.product.ProductNotFoundException;
import com.bhaskar.urlshortner.exception.product.ProductSaveFailedException;
import com.bhaskar.urlshortner.model.common.Category;
import com.bhaskar.urlshortner.model.common.ResponseDTO;
import com.bhaskar.urlshortner.model.shorturl.UrlResponseDTO;
import com.bhaskar.urlshortner.model.product.Product;
import com.bhaskar.urlshortner.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServicesImpl implements ProductServices {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<?> addProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        if (savedProduct != null) {
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        }
        throw new ProductSaveFailedException();
    }

    @Override
    public ResponseEntity<?> addProductList(List<Product> productList) {
        List<Product> savedProductList = productRepository.saveAll(productList);
        if (savedProductList != null) {
            return new ResponseEntity<>(savedProductList, HttpStatus.OK);
        }
        throw new ProductSaveFailedException();
    }

    @Override
    public ResponseEntity<?> removeProduct(Long productId) {
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) return product;
        throw new ProductNotFoundException();
    }

    @Override
    public BigDecimal getProductPrice(Product product) {
        return null;
    }

    @Override
    public BigDecimal getProductPriceByProductId(Long productId) {
        return getProductById(productId).map(product -> product.getPrice()).orElse(null);
    }

    @Override
    public Category getProductCategory(Product product) {
        return null;
    }

    @Override
    public String getProductName(Product product) {
        return null;
    }
}
