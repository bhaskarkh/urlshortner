package com.bhaskar.urlshortner.controller.product;

import com.bhaskar.urlshortner.model.product.Product;
import com.bhaskar.urlshortner.services.product.ProductServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductServicesImpl productServices;

    @PostMapping("addProductList")
    public ResponseEntity<?> addProductList(@RequestBody List<Product> productList) {
        return productServices.addProductList(productList);
    }

    @PostMapping("addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return productServices.addProduct(product);
    }
}
