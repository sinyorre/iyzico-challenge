package com.iyzico.challenge.controller.v1.api;

import com.iyzico.challenge.dto.payment.PaymentRequestDto;
import com.iyzico.challenge.dto.payment.PaymentResponseDto;
import com.iyzico.challenge.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponseDto> pay(@RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto paymentResponseDto = paymentService.pay(paymentRequestDto.getProductId());
        return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
    }
}
