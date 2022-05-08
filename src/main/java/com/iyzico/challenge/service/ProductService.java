package com.iyzico.challenge.service;


import com.iyzico.challenge.model.product.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    void deleteProduct(Long productId);

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product getProductByIdWithLock(Long productId);

    List<Product> getProductsByIds(List<Long> productIds);

    Product updateProduct(Long productId, Product product);
}
