package com.iyzico.challenge.service;

import com.iyzico.challenge.exception.PaymentException;
import com.iyzico.challenge.model.product.Product;
import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value = "iyziPay")
@RequiredArgsConstructor
public class IyziPayMethod implements PaymentMethod {

    private final Options options;

    @Override
    public void pay(Product product) {
        CreatePaymentRequest request = getCreatePaymentRequest(product);

        PaymentCard paymentCard = getPaymentCard();
        request.setPaymentCard(paymentCard);

        Buyer buyer = getBuyer();
        request.setBuyer(buyer);

        Address shippingAddress = getAddress();
        request.setShippingAddress(shippingAddress);

        Address billingAddress = getBillingAddress();
        request.setBillingAddress(billingAddress);

        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem basketItem = getBasketItem(product);
        basketItems.add(basketItem);
        request.setBasketItems(basketItems);
        Payment payment = Payment.create(request, options);
        if (payment.getErrorCode() != null) {
            throw new PaymentException(payment.getErrorMessage());
        }
    }

    private BasketItem getBasketItem(Product product) {
        BasketItem basketItem = new BasketItem();
        basketItem.setId("BI101");
        basketItem.setName(product.getName());
        basketItem.setCategory1("Collectibles");
        basketItem.setCategory2("Accessories");
        basketItem.setItemType(BasketItemType.PHYSICAL.name());
        basketItem.setPrice(product.getPrice());
        return basketItem;
    }

    private Address getBillingAddress() {
        Address billingAddress = new Address();
        billingAddress.setContactName("Jane Doe");
        billingAddress.setCity("Istanbul");
        billingAddress.setCountry("Turkey");
        billingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        billingAddress.setZipCode("34742");
        return billingAddress;
    }

    private Address getAddress() {
        Address shippingAddress = new Address();
        shippingAddress.setContactName("Jane Doe");
        shippingAddress.setCity("Istanbul");
        shippingAddress.setCountry("Turkey");
        shippingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        shippingAddress.setZipCode("34742");
        return shippingAddress;
    }

    private Buyer getBuyer() {
        Buyer buyer = new Buyer();
        buyer.setId("BY789");
        buyer.setName("John");
        buyer.setSurname("Doe");
        buyer.setGsmNumber("+905350000000");
        buyer.setEmail("email@email.com");
        buyer.setIdentityNumber("74300864791");
        buyer.setLastLoginDate("2015-10-05 12:43:35");
        buyer.setRegistrationDate("2013-04-21 15:12:09");
        buyer.setRegistrationAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        buyer.setIp("85.34.78.112");
        buyer.setCity("Istanbul");
        buyer.setCountry("Turkey");
        buyer.setZipCode("34732");
        return buyer;
    }

    private PaymentCard getPaymentCard() {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName("John Doe");
        paymentCard.setCardNumber("5528790000000008");
        paymentCard.setExpireMonth("12");
        paymentCard.setExpireYear("2030");
        paymentCard.setCvc("123");
        paymentCard.setRegisterCard(0);
        return paymentCard;
    }

    private CreatePaymentRequest getCreatePaymentRequest(Product product) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId("123456789");
        request.setPrice(product.getPrice());
        request.setPaidPrice(product.getPrice());
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId("B67832");
        request.setPaymentChannel(PaymentChannel.WEB.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());
        return request;
    }
}
