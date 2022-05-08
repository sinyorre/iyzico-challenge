package com.iyzico.challenge.service;


import com.iyzico.challenge.model.product.Product;

public interface PaymentMethod {
    void pay(Product product);
}
