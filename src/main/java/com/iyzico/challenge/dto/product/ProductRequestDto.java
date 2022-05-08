package com.iyzico.challenge.dto.product;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Min(0)
    private int stock;

    @NotNull
    @Min(0)
    private BigDecimal price;
}
