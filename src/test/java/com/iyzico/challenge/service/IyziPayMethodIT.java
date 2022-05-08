package com.iyzico.challenge.service;

import com.iyzico.challenge.exception.ProductNotFoundException;
import com.iyzico.challenge.model.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@IT
public class IyziPayMethodIT {

    @Autowired
    IyziPayMethod iyziPayMethod;

    @Test
    void shouldSaveProduct() {
        // Given
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");
        dummyProduct.setStock(10);
        dummyProduct.setPrice(BigDecimal.ONE);

        // When
        try {
            iyziPayMethod.pay(dummyProduct);
        } catch (ProductNotFoundException e) {
            fail("Should not have thrown any exception");
        }

        // Then
        assertTrue(true);
    }
}
