package com.iyzico.challenge.service;

import com.iyzico.challenge.exception.ProductNotFoundException;
import com.iyzico.challenge.model.product.Product;
import com.iyzico.challenge.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) return productOptional.get();
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public Product getProductByIdWithLock(Long productId) {
        Optional<Product> productOptional = productRepository.findProductByIdWithPessimisticLock(productId);
        if (productOptional.isPresent()) return productOptional.get();
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        getProductById(productId);
        product.setId(productId);
        return productRepository.save(product);
    }
}
