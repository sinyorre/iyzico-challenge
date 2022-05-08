package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.payment.PaymentResponseDto;
import com.iyzico.challenge.exception.StockDepletedException;
import com.iyzico.challenge.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ProductService productService;

    // TODO: 5/8/2022 if we have more payment methods, it needs to be change (Strategy Pattern, Factory Pattern...)
    @Qualifier("iyziPay")
    @NotNull
    private final PaymentMethod paymentMethod;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentResponseDto pay(Long productId) {
        Product product = productService.getProductByIdWithLock(productId);
        int stock = product.getStock();
        System.out.println("Veritabanından çekilen stock " + stock);
        if (stock <= 0) throw new StockDepletedException("Stock is depleted");
        System.out.println("Ödeme işlemi gerçekleştiriliyor....");
        paymentMethod.pay(product);
        stock--;
        product.setStock(stock);
        productService.saveProduct(product);
        return PaymentResponseDto.builder()
                .localDateTime(LocalDateTime.now())
                .productId(productId)
                .build();
    }
}
