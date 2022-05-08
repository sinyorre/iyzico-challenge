package com.iyzico.challenge.dto.payment;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentRequestDto {
    @NotNull
    Long productId;
}
