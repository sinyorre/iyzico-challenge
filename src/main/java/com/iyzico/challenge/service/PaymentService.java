package com.iyzico.challenge.service;


import com.iyzico.challenge.dto.payment.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto pay(Long productId);
}
