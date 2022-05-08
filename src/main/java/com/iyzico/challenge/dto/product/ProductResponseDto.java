package com.iyzico.challenge.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private String name;
    private String description;
    private int stock;
    private BigDecimal price;
}
