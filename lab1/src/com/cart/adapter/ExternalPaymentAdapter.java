package com.cart.adapter;

import com.cart.payment.Payable;

/**
 * Adapter: адаптує несумісний інтерфейс ExternalPaymentGateway
 * під очікуваний нашою системою інтерфейс Payable. Завдяки цьому
 * сторонній шлюз можна використовувати скрізь, де очікується Payable,
 * не змінюючи ні ExternalPaymentGateway, ні код, що працює з Payable.
 */
public class ExternalPaymentAdapter implements Payable {
    private final ExternalPaymentGateway gateway;

    public ExternalPaymentAdapter(ExternalPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void pay(double amount) {
        gateway.makeTransaction(amount, "UAH");
    }
}