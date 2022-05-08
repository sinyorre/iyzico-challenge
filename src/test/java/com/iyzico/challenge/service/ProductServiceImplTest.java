package com.iyzico.challenge.service;

import com.iyzico.challenge.model.product.Product;
import com.iyzico.challenge.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldSaveProduct() {
        // Given
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");

        // When
        productServiceImpl.saveProduct(dummyProduct);

        // Then
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldDeleteProduct() {
        // Given
        long dummyProductId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setId(dummyProductId);
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(dummyProduct));

        // When
        productServiceImpl.deleteProduct(dummyProductId);

        // Then
        verify(productRepository, times(1)).delete(any(Product.class));
    }

    @Test
    void shouldThrowProductNotFoundWhenDeleteProduct() {
        // Given
        long dummyProductId = 1L;
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productServiceImpl.deleteProduct(dummyProductId);
        });

        // Then
        String expectedMessage = "Product not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldGetAllProducts() {
        // Given
        Product productOne = new Product();
        Product productTwo = new Product();
        List<Product> dummyProducts = new ArrayList<Product>(){{
            add(productOne);
            add(productTwo);
        }};
        when(productRepository.findAll()).thenReturn(dummyProducts);

        // When
        List<Product> products = productServiceImpl.getAllProducts();

        // Then
        assertEquals(dummyProducts.size(), products.size());
    }

    @Test
    void shouldGetProductById() {
        // Given
        long dummyId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setId(dummyId);
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(dummyProduct));

        // When
        Product product = productServiceImpl.getProductById(dummyId);

        // Then
        assertEquals(dummyProduct, product);
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenGetProductById() {
        // Given
        long dummyId = 1L;
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productServiceImpl.getProductById(dummyId);
        });

        // Then
        String expectedMessage = "Product not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getProductsByIds() {
        // Given
        List<Long> dummyProductIds = new ArrayList<Long>(){{
            add(1L);
            add(2L);
        }};

        // When
        productServiceImpl.getProductsByIds(dummyProductIds);

        // Then
        verify(productRepository, times(1)).findAllById(anyList());
    }

    @Test
    void shouldUpdateProduct() {

        // Given
        Long dummyId = 1L;

        Product dummyUpdatedProduct = new Product();
        dummyUpdatedProduct.setId(1L);
        dummyUpdatedProduct.setName("TEST UPDATED NAME");
        dummyUpdatedProduct.setDescription("TEST UPDATED DESCRIPTION");

        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(dummyProduct));
        when(productRepository.save(any(Product.class)))
                .thenReturn(dummyUpdatedProduct);

        // When
        Product product = productServiceImpl.updateProduct(dummyId, dummyUpdatedProduct);


        // Then
        verify(productRepository, times(1)).save(any(Product.class));
        assertAll("Should return updated product",
                () -> assertEquals("TEST UPDATED NAME", product.getName()),
                () -> assertEquals("TEST UPDATED DESCRIPTION", product.getDescription())
        );
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenUpdateProduct() {

        // Given
        Long dummyId = 1L;
        Product dummyUpdatedProduct = new Product();
        dummyUpdatedProduct.setId(1L);
        dummyUpdatedProduct.setName("TEST UPDATED NAME");
        dummyUpdatedProduct.setDescription("TEST UPDATED DESCRIPTION");
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productServiceImpl.updateProduct(dummyId, dummyUpdatedProduct);
        });

        // Then
        String expectedMessage = "Product not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}