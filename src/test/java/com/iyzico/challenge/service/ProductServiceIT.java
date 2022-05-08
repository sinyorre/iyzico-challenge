package com.iyzico.challenge.service;

import com.iyzico.challenge.exception.ProductNotFoundException;
import com.iyzico.challenge.model.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IT
public class ProductServiceIT {

    @Autowired
    ProductService productService;

    final String PRODUCT_NOT_FOUND_EXCEPTION = "Product not found";

    @Test
    void shouldSaveProduct() {
        // Given
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        // When
        Product savedProduct = productService.saveProduct(dummyProduct);

        // Then
        assertEquals("TEST NAME", savedProduct.getName());
    }

    @Test
    void shouldDeleteProduct() {
        // Given
        long dummyProductId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        // When
        try {
            productService.saveProduct(dummyProduct);
            productService.deleteProduct(dummyProductId);
        } catch (ProductNotFoundException e) {
            fail("Should not have thrown any exception");
        }

        // Then
        assertTrue(true);
    }

    @Test
    void shouldGetAllProducts() {
        // Given
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        Product dummyProduct2 = new Product();
        dummyProduct2.setName("TEST NAME 2");
        dummyProduct2.setDescription("TEST DESCRIPTION 2");

        // When
        productService.saveProduct(dummyProduct);
        productService.saveProduct(dummyProduct2);
        List<Product> products = productService.getAllProducts();

        // Then
        assertEquals(2, products.size());
    }

    @Test
    void shouldGetProductById() {
        // Given
        long dummyProductId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        // When
        productService.saveProduct(dummyProduct);
        Product product = productService.getProductById(dummyProductId);

        // Then
        assertEquals(dummyProduct.getName(), product.getName());
    }

    @Test
    void shouldThrowProductNotFoundWhenGetProductById() {
        // Given
        long dummyProductId = 100L;
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        // When
        productService.saveProduct(dummyProduct);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(dummyProductId);
        });

        // Then
        String actualMessage = exception.getMessage();

        assertEquals(PRODUCT_NOT_FOUND_EXCEPTION, actualMessage);
    }

    @Test
    void shouldGetProductsByIds() {
        // Given
        List<Long> dummyProductIds = new ArrayList<Long>(){{
            add(1L);
            add(2L);
        }};

        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        Product dummyProduct2 = new Product();
        dummyProduct2.setName("TEST NAME 2");
        dummyProduct2.setDescription("TEST DESCRIPTION 2");

        // When
        productService.saveProduct(dummyProduct);
        productService.saveProduct(dummyProduct2);
        List<Product> products = productService.getProductsByIds(dummyProductIds);

        // Then
        assertEquals(2, products.size());
    }

    @Test
    void shouldUpdateProduct() {
        // Given
        long dummyProductId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        Product dummyUpdatedProduct = new Product();
        dummyUpdatedProduct.setName("TEST NAME 2");
        dummyUpdatedProduct.setDescription("TEST DESCRIPTION 2");

        // When
        productService.saveProduct(dummyProduct);
        Product updatedProduct = productService.updateProduct(dummyProductId, dummyUpdatedProduct);

        // Then
        assertEquals("TEST NAME 2", updatedProduct.getName());
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenUpdateProduct() {
        // Given
        long dummyProductId = 1L;

        Product dummyUpdatedProduct = new Product();
        dummyUpdatedProduct.setName("TEST NAME 2");
        dummyUpdatedProduct.setDescription("TEST DESCRIPTION 2");

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(dummyProductId, dummyUpdatedProduct);
        });

        // Then
        String actualMessage = exception.getMessage();

        assertEquals(PRODUCT_NOT_FOUND_EXCEPTION, actualMessage);
    }
}
