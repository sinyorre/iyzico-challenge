package com.iyzico.challenge.model.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product extends AbstractEntity {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private int stock;
    private BigDecimal price;
}
