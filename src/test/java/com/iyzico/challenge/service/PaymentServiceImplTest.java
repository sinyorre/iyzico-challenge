package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.payment.PaymentResponseDto;
import com.iyzico.challenge.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    ProductService productService;

    @Mock
    PaymentMethod paymentMethod;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldPay() {
        // Given
        long dummyProductId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");
        dummyProduct.setStock(10);
        dummyProduct.setPrice(BigDecimal.TEN);
        when(productService.getProductByIdWithLock(anyLong())).thenReturn(dummyProduct);
        doNothing().when(paymentMethod).pay(any(Product.class));

        // When
        PaymentResponseDto paymentResponseDto = paymentService.pay(dummyProductId);

        // Then
        assertEquals(dummyProductId, paymentResponseDto.getProductId());
    }

    @Test
    void shouldThrowStockDepletedExceptionWhenPay() {
        // Given
        long dummyProductId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setName("TEST NAME");
        dummyProduct.setDescription("TEST DESCRIPTION");
        dummyProduct.setStock(0);
        dummyProduct.setPrice(BigDecimal.TEN);
        when(productService.getProductByIdWithLock(anyLong())).thenReturn(dummyProduct);

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            paymentService.pay(dummyProductId);
        });

        // Then
        String expectedMessage = "Stock is depleted";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}