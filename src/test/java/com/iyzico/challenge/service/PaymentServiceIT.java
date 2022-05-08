package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.payment.PaymentResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
@Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PaymentServiceIT {

    @Autowired
    PaymentService paymentService;

    @Test
    void shouldSaveProduct() {
        // Given
        long dummyProductId = 1L;

        // When
        PaymentResponseDto paymentResponseDto = paymentService.pay(dummyProductId);

        // Then
        long productId = paymentResponseDto.getProductId();
        assertEquals(dummyProductId, productId);
    }
}
